package br.com.concrete.rodrigorocha.desafiokotlin.handlers

import br.com.concrete.rodrigorocha.desafiokotlin.handlers.exceptions.*
import io.javalin.Javalin

object RegisterHandlers {

    fun register(app: Javalin) {
        AuthorizationHandler.register(app)
        BadRequestHandler.register(app)
        NotFoundHandler.register(app)
        ConflictHandler.register(app)
        UnauthorizedHandler.register(app)
        IllegalArgumentHandler.register(app)
    }
}