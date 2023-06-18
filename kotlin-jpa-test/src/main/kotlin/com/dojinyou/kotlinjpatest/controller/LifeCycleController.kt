package com.dojinyou.kotlinjpatest.controller

import com.dojinyou.kotlinjpatest.entity.LifeCycleEntity
import com.dojinyou.kotlinjpatest.repository.LifeCycleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/lc")
class LifeCycleController(
    private val lifeCycleRepository: LifeCycleRepository,
) {

    @GetMapping("/create")
    fun create(): LifeCycleEntity {
        println("${this::class.simpleName} ${this::create.name} start")
        val entity = LifeCycleEntity()
        val savedEntity = lifeCycleRepository.save(entity)
        println("${this::class.simpleName} ${this::create.name} end")

        return savedEntity
    }

    @GetMapping("/findOrCreate/{id}")
    fun findOrCreate(@PathVariable id: String): LifeCycleEntity {
        println("${this::class.simpleName} ${this::findOrCreate.name} start")
        val entity = lifeCycleRepository.findByIdOrNull(id.toLong()) ?: LifeCycleEntity()
        entity.column1 = "findOrCreate"
        val savedEntity = lifeCycleRepository.save(entity)
        println("${this::class.simpleName} ${this::findOrCreate.name} end")

        return savedEntity
    }

    @GetMapping("/find/{id}")
    fun findById(@PathVariable id: String): LifeCycleEntity {
        println("${this::class.simpleName} ${this::findById.name} start")
        val entity = lifeCycleRepository.findByIdOrNull(id.toLong())!!
        println("${this::class.simpleName} ${this::findById.name} end")

        return entity
    }

    @GetMapping("/update/{id}")
    fun update(@PathVariable id: String): LifeCycleEntity {
        println("${this::class.simpleName} ${this::update.name} start")
        val entity = lifeCycleRepository.findByIdOrNull(id.toLong())!!
        entity.column1 += "updated"
        val savedEntity = lifeCycleRepository.save(entity)
        println("${this::class.simpleName} ${this::update.name} end")

        return savedEntity
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: String) {
        println("${this::class.simpleName} ${this::delete.name} start")
        val entity = lifeCycleRepository.findByIdOrNull(id.toLong())!!
        lifeCycleRepository.delete(entity)
        println("${this::class.simpleName} ${this::delete.name} end")
    }

    @GetMapping("/persist/{id}")
    fun persistTest(@PathVariable(required = false) id: String?): LifeCycleEntity {
        println("${this::class.simpleName} ${this::persistTest.name} start")
        val entity = LifeCycleEntity(id ?: UUID.randomUUID().toString(), UUID.randomUUID().toString())
        val savedEntity = lifeCycleRepository.save(entity)
        println("${this::class.simpleName} ${this::persistTest.name} end")

        return savedEntity
    }

}
