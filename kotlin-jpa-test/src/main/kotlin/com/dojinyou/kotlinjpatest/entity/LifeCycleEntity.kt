package com.dojinyou.kotlinjpatest.entity

import jakarta.persistence.*
import org.springframework.data.domain.Persistable
import java.util.*

@Entity(name = "life_cycle")
class LifeCycleEntity(
    @Id
    private val id: String = UUID.randomUUID().toString(),

    @Column
    var column1: String? = UUID.randomUUID().toString(),
): Persistable<String> {
    @Transient
    private var _isNew: Boolean = true

    override fun getId() = id

    override fun isNew() = _isNew

    @PostLoad
    @PostPersist
    protected fun load() {
        _isNew = false
    }
}
