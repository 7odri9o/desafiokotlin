package br.com.concrete.rodrigorocha.desafiokotlin.web.converters

import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.Phone
import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.User
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.PhoneRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.validators.UserValidator
import io.javalin.Context

class UserRequestToUserConverter(
    private val userValidator: UserValidator
) {

    fun convert(ctx: Context) : User {

        val userRequest =  userValidator.validateUserRequest(ctx)

        return User(
            null,
            userRequest.name,
            userRequest.email,
            userRequest.password,
            userRequest.phones!!.map { convertPhoneRequestToPhone(it) })
    }

    private fun convertPhoneRequestToPhone(phoneRequest: PhoneRequest): Phone {
        return Phone(null, phoneRequest.ddd, phoneRequest.number)
    }
}