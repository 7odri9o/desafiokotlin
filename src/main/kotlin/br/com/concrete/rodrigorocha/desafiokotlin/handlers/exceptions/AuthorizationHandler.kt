package br.com.concrete.rodrigorocha.desafiokotlin.handlers.exceptions

import io.javalin.Javalin
import io.javalin.UnauthorizedResponse

object AuthorizationHandler {

    fun register(app: Javalin) {
        app.before("/api/users/:id") {
            it.header("Authorization").isNullOrEmpty()
                .let { it ->
                    if (it) throw UnauthorizedResponse("Não Autorizado")
                }
        }
    }
}