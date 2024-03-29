package com.dojinyou.kotlinjpatest.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class SeparateEntity(
    id: String = UUID.randomUUID().toString(),
    name: String,
    nullableField: String? = null,
) {
//): Persistable<String> {
    @Id
    val id: String = id

    @Column(nullable = false)
    var name: String = name

    @Column
    var nullableField: String? = nullableField
}
