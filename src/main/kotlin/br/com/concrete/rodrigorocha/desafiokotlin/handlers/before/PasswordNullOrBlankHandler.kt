package br.com.concrete.rodrigorocha.desafiokotlin.handlers.before

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.NewUserDTO
import io.javalin.BadRequestResponse
import io.javalin.Javalin

object PasswordNullOrBlankHandler {

    fun register(app: Javalin) {
        app.before("/users") {
            it.method().takeIf { method -> method == "POST" }
                .apply {
                    val newUser = it.body<NewUserDTO>()
                    if(newUser.password.isNullOrBlank()) {
                        throw BadRequestResponse("O campo Password deve ser preenchido")
                    }
                }
        }
    }
}