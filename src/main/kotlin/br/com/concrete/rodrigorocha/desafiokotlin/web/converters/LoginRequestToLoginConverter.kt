package br.com.concrete.rodrigorocha.desafiokotlin.web.converters

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.LoginDTO
import br.com.concrete.rodrigorocha.desafiokotlin.web.validators.ValidateFields
import io.javalin.Context

class LoginRequestToLoginConverter(
    private val validateFields: ValidateFields
) {

    fun convert(ctx: Context) : LoginDTO {

        val loginRequest =  validateFields.validateLoginRequest(ctx)

        return LoginDTO(
            email = loginRequest.email!!,
            password = loginRequest.password!!)
    }
}