package com.dojinyou.kotlinjpatest.controller

import com.dojinyou.kotlinjpatest.domain.SeparateDomain
import com.dojinyou.kotlinjpatest.entity.SeparateEntity
import com.dojinyou.kotlinjpatest.repository.SeparateJpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

@RestController
@RequestMapping("/separate")
class SeparateController(
    private val separateJpaRepository: SeparateJpaRepository,
) {

    @GetMapping("/create")
    fun create(): String {
        val domain = SeparateDomain(name = "name")
        val entity = SeparateEntity(id = domain.id, name = domain.name, nullableField = domain.nullableField)
        val savedEntity = separateJpaRepository.saveAndFlush(entity)

        return savedEntity.id!!
    }

    @GetMapping("/update/{id}")
    fun updateName(
        @PathVariable("id") id: String,
    ): String {
        val nullableField = "nullableField-${idNumber.getAndIncrement()}"
        val updatedEntity = SeparateEntity(id, "name", nullableField)
        separateJpaRepository.save(updatedEntity)

        return updatedEntity.id!!
    }

    @GetMapping("/update2/{id}")
    fun updateName2(
        @PathVariable("id") id: String,
    ): String {
        val preEntity = separateJpaRepository.findById(id).orElseThrow()
        val domain = SeparateDomain(id = preEntity.id!!, name = preEntity.name, nullableField = preEntity.nullableField)
        val updatedDomain = domain.copy(nullableField = "updated-${idNumber.getAndIncrement()}")
        preEntity.nullableField = updatedDomain.nullableField
        separateJpaRepository.save(preEntity)

        return preEntity.id!!
    }

    @GetMapping("/find/{id}")
    fun findById(
        @PathVariable("id") id: String,
    ): SeparateEntity {
        return separateJpaRepository.findById(id).orElseThrow()
    }

    companion object {
        val idNumber = AtomicInteger()
    }
}
