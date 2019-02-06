package br.com.concrete.rodrigorocha.desafiokotlin.web.handlers

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserResponse
import io.javalin.BadRequestResponse
import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus

//internal data class ErrorResponse(val mensagem: String?)

object ErrorExceptionMapping {

    fun register(app: Javalin) {

        app.exception(BadRequestResponse::class.java) { e, ctx ->
            val error = UserResponse(message = e.message)
            ctx.json(error).status(HttpStatus.BAD_REQUEST_400)
        }
    }
}