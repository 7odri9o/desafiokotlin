package br.com.concrete.rodrigorocha.desafiokotlin.web.converters

import br.com.concrete.rodrigorocha.desafiokotlin.domain.PhoneDTO
import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.NewUserDTO
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.databind.util.Converter

class NewUserToUserConverter: Converter<NewUserDTO, UserDTO> {

    override fun getOutputType(typeFactory: TypeFactory?): JavaType {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun convert(value: NewUserDTO): UserDTO {
        return UserDTO(
            name = value.name,
            email = value.email,
            password = value.password,
            phones = value.phones.map { convertPhoneRequestToPhone(it) })
    }

    override fun getInputType(typeFactory: TypeFactory?): JavaType {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun convertPhoneRequestToPhone(phoneRequest: PhoneDTO): PhoneDTO {
        return PhoneDTO(
            ddd = phoneRequest.ddd,
            number = phoneRequest.number
        )
    }
}