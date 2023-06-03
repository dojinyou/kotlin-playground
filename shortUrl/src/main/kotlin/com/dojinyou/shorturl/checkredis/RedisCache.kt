package com.dojinyou.shorturl.checkredis

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.*
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisCache(
    reactiveRedisConnectionFactory: ReactiveRedisConnectionFactory,
    private val objectMapper: ObjectMapper,
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    private val redisTemplate = ReactiveRedisTemplate(
        reactiveRedisConnectionFactory,
        RedisSerializationContext.newSerializationContext<String, PushInfo>()
            .key(StringRedisSerializer())
            .hashKey(StringRedisSerializer())
            .value(Jackson2JsonRedisSerializer(PushInfo::class.java).also { it.setObjectMapper(objectMapper) })
            .hashValue(Jackson2JsonRedisSerializer(PushInfo::class.java).also { it.setObjectMapper(objectMapper) })
            .build(),
    )

    fun add(info: PushInfo) {
        runBlocking {
            redisTemplate.run { opsForList().rightPushAndAwait(KEY_PREFIX, info) }
        }
    }

    fun pop(): PushInfo? {
        return runBlocking {
            redisTemplate.opsForList().leftPopAndAwait(KEY_PREFIX)
        }
    }

    fun popAll(): List<PushInfo> {
        return runBlocking {
            val opsForList = redisTemplate.opsForList()
            opsForList.rangeAsFlow(KEY_PREFIX, 0, -1).toList()
        }
    }

    fun delete(): Boolean {
        return runBlocking {
            redisTemplate.opsForList().deleteAndAwait(KEY_PREFIX)
        }
    }

    data class PushInfo(
        val input: String,
    )

    companion object {
        private val DEFAULT_EXPIRY = Duration.ofHours(11 + 3) // 11시간(푸시 제한) + 3시간(버퍼)
        private const val KEY_PREFIX = "consultationPush"
    }

}

