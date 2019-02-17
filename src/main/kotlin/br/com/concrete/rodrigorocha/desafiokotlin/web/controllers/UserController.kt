package br.com.concrete.rodrigorocha.desafiokotlin.web.controllers

import br.com.concrete.rodrigorocha.desafiokotlin.service.UserService
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.NewUserToUserConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.UserToUserResponseConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.NewUserDTO
import io.javalin.Context
import io.javalin.UnauthorizedResponse
import org.eclipse.jetty.http.HttpStatus
import java.util.*

class UserController(
    private val newUserToUserConverter: NewUserToUserConverter,
    private val userToUserResponseConverter: UserToUserResponseConverter,
    private val userService: UserService) {

    fun register(ctx: Context) {

        val newUser = ctx.body<NewUserDTO>()

        userService.create(newUserToUserConverter.convert(newUser))
            .apply {
                ctx.status(HttpStatus.CREATED_201)
                val user = userToUserResponseConverter.convert(this)
                ctx.json(user)
            }
    }

    fun getUser(ctx: Context) {

        val id = UUID.fromString(ctx.pathParam("id"))
        val token = ctx.header("Authorization")?.replace("Bearer ", "")

        if(token.isNullOrBlank()) {
            throw UnauthorizedResponse("Não Autorizado")
        }

        val user = userService.getUser(id, token)
        ctx.status(HttpStatus.OK_200)
        ctx.json(user)
    }
}