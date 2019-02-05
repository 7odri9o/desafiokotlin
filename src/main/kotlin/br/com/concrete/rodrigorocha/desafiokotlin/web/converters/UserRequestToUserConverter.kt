package br.com.concrete.rodrigorocha.desafiokotlin.web.converters

import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.Phone
import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.User
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.PhoneRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserRequest

class UserRequestToUserConverter {

    fun convert(userRequest: UserRequest) : User {
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