package com.dojinyou.kotlinjpatest.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class PropertyEntity(
    @Id
    val id: String,
)
