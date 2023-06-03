package com.dojinyou.shorturl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.time.Instant

fun main() {
    val payload = mapOf(Pair("userId", "id"), Pair("nickname", "nickname"))
    val jwt = createJwt(payload = payload, issuer = "issuer", secret = "secret", expiresAt = Instant.now().plusSeconds(1000))
    println(jwt)
    valid(jwt!!)
}
private fun createJwt(
    payload: Map<String, String?>,
    issuer: String,
    secret: String,
    expiresAt: Instant,
): String? {
    val algorithm = Algorithm.HMAC256(secret)
    return JWT.create()
        .withIssuer(issuer)
        .withPayload(payload)
        .withExpiresAt(expiresAt)
        .sign(algorithm)
}

private fun valid(jwt: String): Boolean {
    val algorithm = Algorithm.HMAC256("secret")
    val verifier = JWT.require(algorithm)
        .withIssuer("issuer")
        .build()

    return try {
        val decodedJWT = verifier.verify(jwt)
        println(decodedJWT.claims["nickname"])
        true
    } catch (e: Throwable) {
        println("message = ${e.message}, classname = ${e.javaClass.simpleName}")
        false
    }
}
