package com.dojinyou.shorturl.business

import java.net.URL

private const val protocol = "http"
private const val host = "www.host.com"

object ShortUrlUtils {
    fun toUrl(shortValue: String): URL {
        return URL("$protocol://$host/$shortValue")
    }
}
