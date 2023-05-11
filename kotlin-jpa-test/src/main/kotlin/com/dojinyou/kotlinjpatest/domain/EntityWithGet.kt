package com.dojinyou.kotlinjpatest.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
data class EntityWithGet(
    @get:Id
    var id: String = UUID.randomUUID().toString()
)
