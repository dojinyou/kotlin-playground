package com.dojinyou.kotlinjpatest.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import org.springframework.data.domain.Persistable
import java.util.*

@Entity
class SeparateEntity(
    id: String = UUID.randomUUID().toString(),
    name: String,
    nullableField: String? = null,
): Persistable<String> {
    @Id
    private val id: String = id

    @Column(nullable = false)
    val name: String = name

    @Column
    var nullableField: String? = nullableField

    @Transient
    private var _isNew: Boolean = true

    override fun getId(): String? = id

    @JsonIgnore
    override fun isNew() = _isNew

    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }
}
