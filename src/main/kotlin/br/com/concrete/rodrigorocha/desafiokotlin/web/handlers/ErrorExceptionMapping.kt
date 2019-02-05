package br.com.concrete.rodrigorocha.desafiokotlin.web.handlers

import io.javalin.BadRequestResponse
import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus

internal data class ErrorResponse(val mensagem: String?)

object ErrorExceptionMapping {

    fun register(app: Javalin) {

        app.exception(BadRequestResponse::class.java) { e, ctx ->
            val error = ErrorResponse(e.message)
            ctx.json(error).status(HttpStatus.BAD_REQUEST_400)
        }
    }
}