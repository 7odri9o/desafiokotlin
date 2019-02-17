package br.com.concrete.rodrigorocha.desafiokotlin.web.converters

import br.com.concrete.rodrigorocha.desafiokotlin.domain.PhoneDTO
import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.NewUserDTO
import br.com.concrete.rodrigorocha.desafiokotlin.web.validators.ValidateFields
import io.javalin.Context

class UserRequestToUserConverter(
    private val validateFields: ValidateFields
) {

    fun convert(ctx: Context) : UserDTO {

        val userRequest =  ctx.body<NewUserDTO>()

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