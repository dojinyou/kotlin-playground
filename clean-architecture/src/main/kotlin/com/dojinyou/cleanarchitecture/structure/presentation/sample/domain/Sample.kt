package com.dojinyou.cleanarchitecture.structure.presentation.sample.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Sample(
    @field:Id
    val id: Long,
)
