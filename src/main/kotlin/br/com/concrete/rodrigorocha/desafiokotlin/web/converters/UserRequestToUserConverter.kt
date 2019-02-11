package br.com.concrete.rodrigorocha.desafiokotlin.web.converters

import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.User
import br.com.concrete.rodrigorocha.desafiokotlin.web.validators.UserValidator
import io.javalin.Context

class UserRequestToUserConverter(
    private val userValidator: UserValidator
) {

    fun convert(ctx: Context) : User {

        val userRequest =  userValidator.validateUserRequest(ctx)

        return User(
            null,
            userRequest.name!!,
            userRequest.email!!,
            userRequest.password
        )
    }
}