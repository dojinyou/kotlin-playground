package com.dojinyou.kotlinjpatest.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/typeform")
class TypeFormWebHookController {

    @PostMapping("/webhook")
    fun handle(@RequestBody typeFormPayload: TypeFormPayload) {
        println("input")
        println(typeFormPayload)
    }
}
