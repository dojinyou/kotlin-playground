package com.dojinyou.kotlinjpatest.entity


import jakarta.persistence.*
import java.util.*

@Entity
class Sample(
    @Id
    val id: String = UUID.randomUUID().toString(),

    @Column(length = 32, nullable = false, updatable = false)
    val immutableField: String,
)
