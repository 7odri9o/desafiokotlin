package br.com.concrete.rodrigorocha.desafiokotlin.web.handlers

import io.javalin.*
import org.eclipse.jetty.http.HttpStatus

internal data class ErrorResponse(val message: String?)

object ErrorExceptionMapping {

    fun register(app: Javalin) {

        app.exception(BadRequestResponse::class.java) { e, ctx ->
            val error = ErrorResponse(message = e.message)
            ctx.json(error).status(HttpStatus.BAD_REQUEST_400)
        }

        app.exception(NotFoundResponse::class.java) { _, ctx ->
            val error = ErrorResponse(message = "Endpoint não encontrado")
            ctx.json(error).status(HttpStatus.NOT_FOUND_404)
        }

        app.exception(ConflictResponse::class.java) { e, ctx ->
            val error = ErrorResponse(message = e.message)
            ctx.json(error).status(HttpStatus.CONFLICT_409)
        }

        app.exception(UnauthorizedResponse::class.java) { e, ctx ->
            val error = ErrorResponse(message = e.message)
            ctx.json(error).status(HttpStatus.UNAUTHORIZED_401)
        }
    }
}
