package br.com.concrete.rodrigorocha.desafiokotlin.web.validators

import br.com.concrete.rodrigorocha.desafiokotlin.domain.extensions.isEmailInvalid
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserRequest
import io.javalin.BadRequestResponse
import io.javalin.Context

class UserValidator {

    fun validateUserRequest(ctx: Context): UserRequest {

        val userRequest = ctx.body<UserRequest>()

        if (userRequest.name.isNullOrBlank() || userRequest.name.isEmpty()) {
            throw BadRequestResponse("O Campo Name deve ser preenchido")
        }

        if (userRequest.email.isNullOrBlank() || userRequest.email.isEmpty()) {
            throw BadRequestResponse("O Campo Email deve ser preenchido")
        }

        if (userRequest.email.isEmailInvalid()) {
            throw BadRequestResponse("Formato de email inválido")
        }

        if (userRequest.password.isNullOrBlank() || userRequest.password.isEmpty()) {
            throw BadRequestResponse("O Campo Password deve ser preenchido")
        }

//        if (userRequest.phones.isNullOrEmpty()) {
//            throw BadRequestResponse("O Campo Phones deve ser preenchido")
//        }
//
//        val hasInvalidPhoneDDD = userRequest.phones!!.any { it.ddd.isNullOrBlank() || it.ddd.isEmpty() }
//        if (hasInvalidPhoneDDD) throw BadRequestResponse("O Campo DDD deve ser preenchido")
//
//        val hasInvalidPhoneNumber = userRequest.phones!!.any { it.number.isNullOrBlank() || it.number.isEmpty() }
//        if (hasInvalidPhoneNumber) throw BadRequestResponse("O Campo Number deve ser preenchido")

        return userRequest
    }
}