package com.dojinyou.kotlinjpatest.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.net.URLEncoder
import java.nio.charset.Charset
import kotlin.text.Charsets.UTF_8

@RestController
@RequestMapping("/encode")
class EncodeController {

    @GetMapping("/default")
    fun encodeDefault(
        @RequestParam("text", required = true) text: String,
    ): String {
        return URLEncoder.encode(text, Charset.defaultCharset())
    }

    @GetMapping("/utf8")
    fun encodeUTF8(
        @RequestParam("text", required = true) text: String,
    ): String {
        return URLEncoder.encode(text, UTF_8)
    }

    @GetMapping("/cp949")
    fun encodeCP949(
        @RequestParam("text", required = true) text: String,
    ): String {
        return URLEncoder.encode(text, Charset.forName("CP949"))
    }

    @GetMapping("/euc-kr")
    fun encodeEucKr(
        @RequestParam("text", required = true) text: String,
    ): String {
        return URLEncoder.encode(text, Charset.forName("EUC-KR"))
    }

    @GetMapping("/origin")
    fun encodeOrigin(
        @RequestParam("text", required = true) text: String,
    ): String {
        val uriString = UriComponentsBuilder.fromUriString("encode.test.com")
            .path("path")
            .toUriString()
        return "$uriString#$text"
    }
}
