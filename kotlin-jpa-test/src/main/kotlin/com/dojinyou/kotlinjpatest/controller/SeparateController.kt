package com.dojinyou.kotlinjpatest.controller

import com.dojinyou.kotlinjpatest.domain.SeparateDomain
import com.dojinyou.kotlinjpatest.entity.FkEntity
import com.dojinyou.kotlinjpatest.entity.SeparateEntity
import com.dojinyou.kotlinjpatest.repository.FkJpaRepository
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
    private val fkJpaRepository: FkJpaRepository,
) {

    @GetMapping("/create")
    fun create(): String {
        val domain = SeparateDomain(name = "name")
        val entity = SeparateEntity(id = domain.id, name = domain.name, nullableField = domain.nullableField)
        val savedEntity = separateJpaRepository.saveAndFlush(entity)

        return savedEntity.id!!
    }

    @GetMapping("/createfk/{id}")
    fun createFk(
        @PathVariable("id") id: String,
    ): String {
        val entity = findById(id)
        val fk = FkEntity(name = "name2", separateEntity = entity)

        fkJpaRepository.save(fk)

        return fk.id
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

    @GetMapping("/update-with-fk/{id}")
    fun updateWithFk(
        @PathVariable("id") id: String,
    ) {
        val preEntities = fkJpaRepository.findAllBySeparateEntityId(id)
        if (preEntities.isEmpty()) throw IllegalStateException()

        val fkEntity = preEntities[0]
        val updatedName = "updatedName"
        fkEntity.name = updatedName
        fkEntity.separateEntity.name = updatedName

        fkJpaRepository.save(fkEntity)
    }

    @GetMapping("/find/{id}")
    fun findById(
        @PathVariable("id") id: String,
    ): SeparateEntity {
        return separateJpaRepository.findById(id).orElseThrow()
    }

    @GetMapping("/delete/{id}")
    fun deleteById(
        @PathVariable("id") id: String,
    ) {
        val entity = findById(id);
        separateJpaRepository.delete(entity)
    }

    companion object {
        val idNumber = AtomicInteger()
    }
}
