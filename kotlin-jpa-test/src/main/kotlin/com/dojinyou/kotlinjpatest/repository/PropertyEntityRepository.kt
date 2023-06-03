package com.dojinyou.kotlinjpatest.repository

import com.dojinyou.kotlinjpatest.entity.PropertyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PropertyEntityRepository : JpaRepository<PropertyEntity, String>
