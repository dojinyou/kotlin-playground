package com.dojinyou.shorturl.repository

import java.net.URL

interface ShortUrlRepository {
    fun existLongUrl(longUrl: URL): Boolean

    fun findLongUrl(shortValue: String): URL?

    fun save(shortUrl: ShortUrl)
}
