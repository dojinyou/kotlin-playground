package com.dojinyou.cleanarchitecture.structure.presentation.sample.adapter.out.persistence

import com.dojinyou.cleanarchitecture.structure.presentation.sample.application.port.out.SamplePort
import org.springframework.stereotype.Component

@Component
class SamplePersistenceAdapter : SamplePort {
    override fun save(sample: String) {
        println("save sample")
    }
}
