package br.com.concrete.rodrigorocha.desafiokotlin.handlers.before

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.NewUserDTO
import io.javalin.BadRequestResponse
import io.javalin.Javalin

object PhoneDDDNullOrEmptyHandler {

    fun register(app: Javalin) {
        app.before("/users") {
            it.method().takeIf { method -> method == "POST" }
                .apply {
                    val newUser = it.body<NewUserDTO>()

                    val hasInvalidPhoneDDD = newUser.phones.any { phone -> phone.ddd.isNullOrBlank() || phone.ddd.isEmpty() }
                    if (hasInvalidPhoneDDD) throw BadRequestResponse("O Campo DDD deve ser preenchido")
                }
        }
    }
}