package com.dojinyou.kotlinjpatest.repository

import com.dojinyou.kotlinjpatest.entity.FkEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FkJpaRepository : JpaRepository<FkEntity, String> {
    fun findAllBySeparateEntityId(separateEntityId: String): List<FkEntity>
}
