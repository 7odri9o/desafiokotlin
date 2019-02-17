package br.com.concrete.rodrigorocha.desafiokotlin.handlers.before

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.NewUserDTO
import io.javalin.BadRequestResponse
import io.javalin.Javalin

object NameEmptyOrNullHandler {

    fun register(app: Javalin) {
        app.before("/users") {
            it.method().takeIf { method -> method == "POST" }
                .apply {
                    val newUser = it.body<NewUserDTO>()
                    if(newUser.name.isNullOrBlank()) {
                        throw BadRequestResponse("O campo Name deve ser preenchido")
                    }
                }
        }
    }
}