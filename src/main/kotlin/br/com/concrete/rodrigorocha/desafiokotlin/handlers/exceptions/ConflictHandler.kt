package br.com.concrete.rodrigorocha.desafiokotlin.handlers.exceptions

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.ErrorResponse
import io.javalin.ConflictResponse
import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus

object ConflictHandler {

    fun register(app: Javalin) {
        app.exception(ConflictResponse::class.java) { e, ctx ->
            val error = ErrorResponse(message = e.message)
            ctx.json(error).status(HttpStatus.CONFLICT_409)
        }
    }
}