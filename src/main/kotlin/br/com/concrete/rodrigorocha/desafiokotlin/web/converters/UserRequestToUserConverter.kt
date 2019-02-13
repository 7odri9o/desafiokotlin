package br.com.concrete.rodrigorocha.desafiokotlin.web.converters

import br.com.concrete.rodrigorocha.desafiokotlin.domain.PhoneDTO
import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import br.com.concrete.rodrigorocha.desafiokotlin.web.validators.UserValidator
import io.javalin.Context

class UserRequestToUserConverter(
    private val userValidator: UserValidator
) {

    fun convert(ctx: Context) : UserDTO {

        val userRequest =  userValidator.validateUserRequest(ctx)

        return UserDTO(
            name = userRequest.name!!,
            email = userRequest.email!!,
            password = userRequest.password!!,
            phones = userRequest.phones.map { convertPhoneRequestToPhone(it) })
    }

    private fun convertPhoneRequestToPhone(phoneRequest: PhoneDTO): PhoneDTO {
        return PhoneDTO(
            ddd = phoneRequest.ddd,
            number = phoneRequest.number
        )
    }
}