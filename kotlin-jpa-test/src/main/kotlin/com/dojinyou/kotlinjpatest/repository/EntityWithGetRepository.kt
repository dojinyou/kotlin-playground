package com.dojinyou.kotlinjpatest.repository

import com.dojinyou.kotlinjpatest.domain.EntityWithGet
import org.springframework.data.jpa.repository.JpaRepository

interface EntityWithGetRepository : JpaRepository<EntityWithGet, String>
