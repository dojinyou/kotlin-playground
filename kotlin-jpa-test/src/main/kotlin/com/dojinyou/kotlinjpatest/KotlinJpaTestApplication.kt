package com.dojinyou.kotlinjpatest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinJpaTestApplication

fun main(args: Array<String>) {
    runApplication<KotlinJpaTestApplication>(*args)
}
