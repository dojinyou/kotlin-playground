package com.dojinyou.kotlinjpatest.repository

import com.dojinyou.kotlinjpatest.entity.DynamicUpdateEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DynamicUpdateRepository : JpaRepository<DynamicUpdateEntity, Long?> {
}
