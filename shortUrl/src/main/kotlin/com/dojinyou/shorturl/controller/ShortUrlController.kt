package com.dojinyou.shorturl.controller

import com.dojinyou.shorturl.business.ShortUrlService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URL

@RestController
class ShortUrlController(
    val shortUrlService: ShortUrlService
) {

    @PostMapping("data/shorten")
    fun createShortUrl(@RequestBody request: Request): Response {
        val shortUrl = shortUrlService.create(request.longUrl)
        return Response(shortUrl)
    }

    data class Request(val longUrl: URL)

    data class Response(val shortUrl: URL)
}
