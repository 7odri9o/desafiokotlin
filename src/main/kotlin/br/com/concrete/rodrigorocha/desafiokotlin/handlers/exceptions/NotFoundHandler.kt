package br.com.concrete.rodrigorocha.desafiokotlin.handlers.exceptions

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.ErrorResponse
import io.javalin.Javalin
import io.javalin.NotFoundResponse
import org.eclipse.jetty.http.HttpStatus

object NotFoundHandler {

    fun register(app: Javalin) {
        app.exception(NotFoundResponse::class.java) { _, ctx ->
            val error = ErrorResponse(message = "Endpoint não encontrado")
            ctx.json(error).status(HttpStatus.NOT_FOUND_404)
        }
    }
}