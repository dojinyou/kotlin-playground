package com.dojinyou.shorturl.repository

import org.springframework.stereotype.Repository
import java.net.URL

@Repository
class DefaultShortUrlRepository(
    private val shortUrlJpaRepository: ShortUrlJpaRepository
) : ShortUrlRepository {
    override fun existLongUrl(longUrl: URL): Boolean {
        return shortUrlJpaRepository.existsByLongUrl(longUrl)
    }

    override fun findLongUrl(shortValue: String): URL? {
        return shortUrlJpaRepository.findByShortValue(shortValue)?.longUrl
    }

    override fun save(shortUrl: ShortUrl) {
        shortUrlJpaRepository.save(shortUrl)
    }

}
