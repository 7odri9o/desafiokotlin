package br.com.concrete.rodrigorocha.desafiokotlin.handlers.before

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.NewUserDTO
import io.javalin.BadRequestResponse
import io.javalin.Javalin

object PhoneNumberNullOrEmptyHandler {

    fun register(app: Javalin) {
        app.before("/users") {
            it.method().takeIf { method -> method == "POST" }
                .apply {
                    val newUser = it.body<NewUserDTO>()

                    val hasInvalidPhoneNumber = newUser.phones.any { phone -> phone.number.isNullOrBlank() || phone.ddd.isEmpty() }
                    if (hasInvalidPhoneNumber) throw BadRequestResponse("O Campo Number deve ser preenchido")
                }
        }
    }
}