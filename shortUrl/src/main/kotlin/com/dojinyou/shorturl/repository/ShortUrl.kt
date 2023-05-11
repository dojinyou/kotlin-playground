package com.dojinyou.shorturl.repository

import java.net.URL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "short_url")
data class ShortUrl(
    @Id
    val id: String,

    @Column(nullable = false)
    val longUrl: URL,

    @Column(nullable = false)
    val shortValue: String,
)
