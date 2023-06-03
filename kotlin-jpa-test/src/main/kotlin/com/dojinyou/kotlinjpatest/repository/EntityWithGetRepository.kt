package com.dojinyou.kotlinjpatest.repository

import com.dojinyou.kotlinjpatest.entity.EntityWithGet
import org.springframework.data.jpa.repository.JpaRepository

interface EntityWithGetRepository : JpaRepository<EntityWithGet, String>
