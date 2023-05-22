package com.dojinyou.kotlinjpatest.controller

import com.dojinyou.kotlinjpatest.domain.EntityWithGet
import com.dojinyou.kotlinjpatest.domain.PropertyEntity
import com.dojinyou.kotlinjpatest.domain.Sample
import com.dojinyou.kotlinjpatest.repository.EntityWithGetRepository
import com.dojinyou.kotlinjpatest.repository.PropertyEntityRepository
import com.dojinyou.kotlinjpatest.repository.SampleRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class SampleController(
    private val sampleRepository: SampleRepository,
    private val entityWithGetRepository: EntityWithGetRepository,
    private val propertyEntityRepository: PropertyEntityRepository,
) {

    @GetMapping("createSample")
    fun createSample() {
        val sample = Sample(immutableField = "test")
        sampleRepository.save(sample)
    }

    @GetMapping("findAllSample")
    fun findALlSample(): List<Sample> {
        return sampleRepository.findAll()
    }

    @GetMapping("get")
    fun get(): List<Any> {
        val a = entityWithGetRepository.findAll()
        val b = propertyEntityRepository.findAll()

        return listOf(a, b)
    }

    @GetMapping("create")
    fun create(): List<Any> {
        val a = createEntityWithGet()
        val b = createPropertyEntity()

        return listOf(a, b)
    }

    private fun createEntityWithGet(): EntityWithGet {
        print("${EntityWithGet::class.simpleName} create start")
        val entity = EntityWithGet()
        println("-> end")
        print("${EntityWithGet::class.simpleName} save start")
        val savedEntity = entityWithGetRepository.save(entity)
        println("-> end")

        return savedEntity
    }

    private fun createPropertyEntity(): PropertyEntity {
        print("${PropertyEntity::class.simpleName} create start")
        val entity = PropertyEntity(id = UUID.randomUUID().toString())
        println("-> end")
        print("${PropertyEntity::class.simpleName} save start")
        val savedEntity = propertyEntityRepository.save(entity)
        println("-> end")

        return savedEntity
    }
}
