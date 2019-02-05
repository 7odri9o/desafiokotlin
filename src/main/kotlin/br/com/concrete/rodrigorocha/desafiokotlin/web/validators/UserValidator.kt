package br.com.concrete.rodrigorocha.desafiokotlin.web.validators

import br.com.concrete.rodrigorocha.desafiokotlin.domain.extensions.isEmailValid
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserRequest
import io.javalin.Context

class UserValidator {

    fun validateUserRequest(ctx: Context): UserRequest {
        return ctx.validatedBody<UserRequest>()
            .check({ !it.email.isNullOrBlank()}, "O Campo Email não pode estar vazio")
            .check({ it.email.isEmailValid()}, "Formato de Email Inválido")
            .getOrThrow()
    }
}