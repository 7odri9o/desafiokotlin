package br.com.concrete.rodrigorocha.desafiokotlin.handlers

import br.com.concrete.rodrigorocha.desafiokotlin.handlers.before.*
import br.com.concrete.rodrigorocha.desafiokotlin.handlers.exceptions.*
import io.javalin.Javalin

object RegisterHandlers {

    fun register(app: Javalin) {
        AuthorizationHandler.register(app)
        NameEmptyOrNullHandler.register(app)
        EmailEmptyOrNullHandler.register(app)
        PasswordNullOrBlankHandler.register(app)
        PhonesNullOrEmptyHandler.register(app)
        PhoneDDDNullOrEmptyHandler.register(app)
        PhoneNumberNullOrEmptyHandler.register(app)

        BadRequestHandler.register(app)
        NotFoundHandler.register(app)
        ConflictHandler.register(app)
        UnauthorizedHandler.register(app)
        IllegalArgumentHandler.register(app)
    }
}