package com.dojinyou.kotlinjpatest.controller

import com.dojinyou.kotlinjpatest.domain.EntityWithGet
import com.dojinyou.kotlinjpatest.domain.PropertyEntity
import com.dojinyou.kotlinjpatest.repository.EntityWithGetRepository
import com.dojinyou.kotlinjpatest.repository.PropertyEntityRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
    private val entityWithGetRepository: EntityWithGetRepository,
    private val propertyEntityRepository: PropertyEntityRepository,
) {

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
        val entity = PropertyEntity()
        println("-> end")
        print("${PropertyEntity::class.simpleName} save start")
        val savedEntity = propertyEntityRepository.save(entity)
        println("-> end")

        return savedEntity
    }
}
