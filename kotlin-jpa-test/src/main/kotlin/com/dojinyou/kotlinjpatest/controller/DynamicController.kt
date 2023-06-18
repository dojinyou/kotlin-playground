package com.dojinyou.kotlinjpatest.controller

import com.dojinyou.kotlinjpatest.entity.DynamicUpdateEntity
import com.dojinyou.kotlinjpatest.repository.DynamicUpdateRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dynamic")
class DynamicController(
    private val dynamicUpdateRepository: DynamicUpdateRepository,
) {

    @GetMapping("/create")
    fun create(): DynamicUpdateEntity {
        val entity = DynamicUpdateEntity()
        return dynamicUpdateRepository.save(entity)
    }

    @GetMapping("/update/{id}/all")
    fun updateAll(@PathVariable id: String): DynamicUpdateEntity {
        val entity = dynamicUpdateRepository.findById(id.toLong()).orElseThrow()
        entity.dynamicUpdatedField1 += "updated"
        entity.dynamicUpdatedField2 += "updated"
        return dynamicUpdateRepository.save(entity)
    }

    @GetMapping("/update/{id}/1")
    fun update1(@PathVariable id: String): DynamicUpdateEntity {
        val entity = dynamicUpdateRepository.findById(id.toLong()).orElseThrow()
        entity.dynamicUpdatedField1 += "updated"
        return dynamicUpdateRepository.save(entity)
    }

    @GetMapping("/update/{id}/2")
    fun update2(@PathVariable id: String): DynamicUpdateEntity {
        val entity = dynamicUpdateRepository.findById(id.toLong()).orElseThrow()
        entity.dynamicUpdatedField2 += "updated"
        return dynamicUpdateRepository.save(entity)
    }
}
