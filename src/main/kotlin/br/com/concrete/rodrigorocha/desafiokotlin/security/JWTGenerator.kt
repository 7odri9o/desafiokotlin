package br.com.concrete.rodrigorocha.desafiokotlin.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.javalin.json.JavalinJackson
import org.joda.time.DateTime
import java.util.*

object JWTGenerator {

    fun sign(subject: Any, creationDate: DateTime,  maxAgeInMinutes: Int) : String {
        val algorithm = Algorithm.HMAC256("secretKey")
        val subjectValue = JavalinJackson.getObjectMapper().writeValueAsString(subject)
        return JWT
            .create()
            .withIssuer("Super Issue")
            .withSubject(subjectValue)
            .withExpiresAt(plusMinutes(creationDate, maxAgeInMinutes))
            .sign(algorithm)
    }

    private fun plusMinutes(now: DateTime, minutes: Int): Date {
        return now.plusMinutes(30).toDate()
    }
}