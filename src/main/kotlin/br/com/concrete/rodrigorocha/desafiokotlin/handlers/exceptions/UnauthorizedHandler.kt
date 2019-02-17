package br.com.concrete.rodrigorocha.desafiokotlin.handlers.exceptions

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.ErrorResponse
import io.javalin.Javalin
import io.javalin.UnauthorizedResponse
import org.eclipse.jetty.http.HttpStatus

object UnauthorizedHandler {

    fun register(app: Javalin) {
        app.exception(UnauthorizedResponse::class.java) { e, ctx ->
            val error = ErrorResponse(message = e.message)
            ctx.json(error).status(HttpStatus.UNAUTHORIZED_401)
        }
    }
}