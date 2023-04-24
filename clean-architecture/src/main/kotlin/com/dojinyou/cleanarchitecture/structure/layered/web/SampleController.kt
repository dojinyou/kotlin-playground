package com.dojinyou.cleanarchitecture.structure.layered.web

import com.dojinyou.cleanarchitecture.structure.layered.domain.SampleRepository
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
