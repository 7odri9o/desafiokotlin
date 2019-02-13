package br.com.concrete.rodrigorocha.desafiokotlin.web.validators

import br.com.concrete.rodrigorocha.desafiokotlin.extensions.isEmailInvalid
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.NewUserDTO
import io.javalin.BadRequestResponse
import io.javalin.Context

class UserValidator {

    fun validateUserRequest(ctx: Context): NewUserDTO {

        val request = ctx.body<NewUserDTO>()

        if (request.name.isNullOrBlank() || request.name.isEmpty()) {
            throw BadRequestResponse("O Campo Name deve ser preenchido")
        }

        if (request.email.isNullOrBlank() || request.email.isEmpty()) {
            throw BadRequestResponse("O Campo Email deve ser preenchido")
        }

        if (request.email.isEmailInvalid()) {
            throw BadRequestResponse("Formato de email inválido")
        }

        if (request.password.isNullOrBlank() || request.password.isEmpty()) {
            throw BadRequestResponse("O Campo Password deve ser preenchido")
        }

        if (request.phones.isNullOrEmpty()) {
            throw BadRequestResponse("O Campo Phones deve ser preenchido")
        }

        val hasInvalidPhoneDDD = request.phones.any { it.ddd.isNullOrBlank() || it.ddd.isEmpty() }
        if (hasInvalidPhoneDDD) throw BadRequestResponse("O Campo DDD deve ser preenchido")

        val hasInvalidPhoneNumber = request.phones.any { it.number.isNullOrBlank() || it.number.isEmpty() }
        if (hasInvalidPhoneNumber) throw BadRequestResponse("O Campo Number deve ser preenchido")

        return request
    }
}