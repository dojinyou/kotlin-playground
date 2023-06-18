package com.dojinyou.kotlinjpatest.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.DynamicUpdate

@Entity(name = "dynamic")
@DynamicUpdate
class DynamicUpdateEntity {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Column
    var dynamicUpdatedField1: String = "init"

    @Column
    var dynamicUpdatedField2: String = "init"
}
