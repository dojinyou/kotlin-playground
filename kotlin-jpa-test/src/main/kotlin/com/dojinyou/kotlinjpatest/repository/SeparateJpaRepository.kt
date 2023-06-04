package com.dojinyou.kotlinjpatest.repository

import com.dojinyou.kotlinjpatest.entity.SeparateEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SeparateJpaRepository : JpaRepository<SeparateEntity, String>
