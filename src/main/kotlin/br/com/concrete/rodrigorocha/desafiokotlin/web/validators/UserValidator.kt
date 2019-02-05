package br.com.concrete.rodrigorocha.desafiokotlin.web.validators

import br.com.concrete.rodrigorocha.desafiokotlin.domain.extensions.isEmailValid
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserRequest
import io.javalin.Context

class UserValidator {

    fun validateUserRequest(ctx: Context): UserRequest {

        return ctx.validatedBody<UserRequest>()
            .check({ !it.email.isNullOrBlank()}, "O Campo Email deve ser preenchido")
            .check({ it.email.isEmailValid()},"Formato de Email Inválido")
            .check({ it.name.isNullOrBlank()}, "O Campo Name deve ser preenchido")
            .check({ it.password.isNullOrBlank()}, "O Campo Password deve ser preenchido")
            .check({ it.phones!!.any { phone ->  phone.ddd.isNullOrBlank() } }, "DDD Inválido")
            .check({ it.phones!!.any { phone ->  phone.number.isNullOrBlank() } }, "Número de Telefone Inválido")
            .getOrThrow()
    }
}