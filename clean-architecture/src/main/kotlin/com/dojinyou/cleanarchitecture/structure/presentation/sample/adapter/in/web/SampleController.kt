package com.dojinyou.cleanarchitecture.structure.presentation.sample.adapter.`in`.web

import com.dojinyou.cleanarchitecture.structure.presentation.sample.application.port.`in`.SampleUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sample")
class SampleController(
    private val sampleUseCase: SampleUseCase,
) {

    @GetMapping
    fun handle(): String {
        return sampleUseCase.sample()
    }
}
