package com.dojinyou.shorturl.checkredis

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("redis")
class RedisTestController(
    private val redisCache: RedisCache,
) {

    @GetMapping("/{value}")
    @ResponseStatus(HttpStatus.OK)
    fun add(@PathVariable("value") value: String) {
        val info = RedisCache.PushInfo(input = value)
        redisCache.add(info)
    }

    @GetMapping("/single")
    @ResponseStatus(HttpStatus.OK)
    fun pop(): RedisCache.PushInfo? {
        return redisCache.pop()
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun popAll(): List<RedisCache.PushInfo> {
        return redisCache.popAll()
    }

    @GetMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    fun delete(): Boolean {
        return redisCache.delete()
    }
}
