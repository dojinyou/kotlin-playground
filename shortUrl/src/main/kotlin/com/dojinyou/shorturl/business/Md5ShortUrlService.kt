package com.dojinyou.shorturl.business

import com.dojinyou.shorturl.repository.ShortUrl
import com.dojinyou.shorturl.repository.ShortUrlRepository
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import java.net.URL

@Primary
@Service
class Md5ShortUrlService(
    private val shortUrlRepository: ShortUrlRepository,
    private val idGenerator: IdGenerator,
) : ShortUrlService {
    override fun create(longUrl: URL): URL {
        // 중복에 대한 처리 필요
        val id = idGenerator.create(longUrl)
        val shortValue = createShortValue(longUrl)

        val entity = ShortUrl(id, longUrl, shortValue)
        shortUrlRepository.save(entity)

        return ShortUrlUtils.toUrl(entity.shortValue)
    }

    private fun createShortValue(longUrl: URL): String {
        return DigestUtils.md5Digest(longUrl.toString().toByteArray()).copyOfRange(0, SHORT_VALUE_MAX_LENGTH).toString()
    }

    companion object {
        const val SHORT_VALUE_MAX_LENGTH = 8
    }

}
