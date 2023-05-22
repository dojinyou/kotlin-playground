package com.dojinyou.kotlinjpatest.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class PropertyEntity(
    @Id
    val id: String,
)
