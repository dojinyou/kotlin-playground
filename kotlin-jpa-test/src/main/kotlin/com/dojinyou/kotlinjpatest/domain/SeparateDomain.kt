package com.dojinyou.kotlinjpatest.domain

import java.util.*


data class SeparateDomain(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val nullableField: String? = null,
)
