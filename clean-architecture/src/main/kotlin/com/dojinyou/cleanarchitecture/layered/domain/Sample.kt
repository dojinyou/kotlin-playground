package com.dojinyou.cleanarchitecture.layered.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Sample(
    @field:Id
    val id: Long,
)
