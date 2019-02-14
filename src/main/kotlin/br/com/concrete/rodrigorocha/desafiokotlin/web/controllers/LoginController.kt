package br.com.concrete.rodrigorocha.desafiokotlin.web.controllers

import br.com.concrete.rodrigorocha.desafiokotlin.domain.PhoneDTO
import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import br.com.concrete.rodrigorocha.desafiokotlin.util.getNow
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus
import java.util.*

class LoginController {

    fun login(ctx: Context) {
        ctx.status(HttpStatus.OK_200)
        val currentDate = getNow()
        val user = UserDTO(
            id = UUID.randomUUID(),
            name = "Nome",
            email = "email@email.com.br",
            phones = listOf(PhoneDTO(ddd = "11", number = "12345678")),
            created = currentDate,
            modified = currentDate,
            lastLogin = currentDate,
            token =  "TOKEN"
        )
        ctx.json(user)
    }
}