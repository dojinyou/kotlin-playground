package com.dojinyou.cleanarchitecture.feature.sample

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sample")
class SampleController(
    private val sampleRepository: SampleRepository,
) {

    @GetMapping
    fun sample(): String {
        return "sample"
    }
}
