package com.dojinyou.cleanarchitecture.structure.presentation.sample.application.port

import com.dojinyou.cleanarchitecture.structure.presentation.sample.application.port.`in`.SampleUseCase
import com.dojinyou.cleanarchitecture.structure.presentation.sample.application.port.out.SamplePort
import org.springframework.stereotype.Component

@Component
class SampleService(
    private val samplePort: SamplePort,
) : SampleUseCase {
    override fun sample(): String {
        samplePort.save("sample")
        return "sample"
    }
}
