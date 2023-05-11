package com.dojinyou.shorturl.business

import java.net.URL

interface IdGenerator {
    fun create(longUrl: URL): String
}
