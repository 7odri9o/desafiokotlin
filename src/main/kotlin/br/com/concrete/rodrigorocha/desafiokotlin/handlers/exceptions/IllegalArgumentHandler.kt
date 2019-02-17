package br.com.concrete.rodrigorocha.desafiokotlin.handlers.exceptions

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.ErrorResponse
import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus

object IllegalArgumentHandler {

    fun register(app: Javalin) {
        app.exception(IllegalArgumentException::class.java) { e, ctx ->
            val error = ErrorResponse(message = e.message)
            ctx.json(error).status(HttpStatus.BAD_REQUEST_400)
        }
    }
}