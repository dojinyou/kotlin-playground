package com.dojinyou.kotlinjpatest.domain


import jakarta.persistence.*
import java.util.*

@Entity
class Sample(
    @Id
    val id: String = UUID.randomUUID().toString(),

    @get:Access(AccessType.PROPERTY)
    @get:Column(length = 32, nullable = false, updatable = false)
    val immutableField: String,
)
