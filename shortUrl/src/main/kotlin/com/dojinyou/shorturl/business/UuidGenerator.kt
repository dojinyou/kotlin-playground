package com.dojinyou.shorturl.business

import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.springframework.stereotype.Component
import java.net.URL

@Component
class UuidGenerator : IdGenerator {
    private val generator = ObjectIdGenerators.UUIDGenerator()

    override fun create(longUrl: URL): String {
        return generator.generateId(longUrl).toString()
    }
}
