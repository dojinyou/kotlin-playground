package com.dojinyou.shorturl.repository

import org.springframework.data.jpa.repository.JpaRepository
import java.net.URL

interface ShortUrlJpaRepository : JpaRepository<ShortUrl, String> {
    fun findByShortValue(shortValue: String): ShortUrl?
    fun existsByLongUrl(longUrl: URL): Boolean
}
