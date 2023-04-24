package com.dojinyou.cleanarchitecture.presentation.sample.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Sample(
    @field:Id
    val id: Long,
)
