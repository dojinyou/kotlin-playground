package com.dojinyou.kotlinjpatest.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
data class EntityWithGetAndVal(

    @get:Id
    val id: String = UUID.randomUUID().toString()
)
