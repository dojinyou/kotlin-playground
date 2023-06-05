package com.dojinyou.kotlinjpatest.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
class FkEntity(
    id: String = UUID.randomUUID().toString(),
    name: String,
    separateEntity: SeparateEntity,
) {
//): Persistable<String> {
    @Id
    val id: String = id

    @Column(nullable = false)
    var name: String = name

    @ManyToOne
    val separateEntity: SeparateEntity = separateEntity
}
