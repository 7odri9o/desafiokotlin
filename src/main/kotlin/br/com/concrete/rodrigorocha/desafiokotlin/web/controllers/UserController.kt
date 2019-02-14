package br.com.concrete.rodrigorocha.desafiokotlin.web.controllers

import br.com.concrete.rodrigorocha.desafiokotlin.service.UserService
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.UserRequestToUserConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.UserToUserResponseConverter
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class UserController(
    private val userRequestToUserConverter: UserRequestToUserConverter,
    private val userToUserResponseConverter: UserToUserResponseConverter,
    private val userService: UserService) {

    fun register(ctx: Context) {
        userService.create(userRequestToUserConverter.convert(ctx))
            .apply {
                ctx.status(HttpStatus.CREATED_201)
                val user = userToUserResponseConverter.convert(this)
                ctx.json(user)
            }
    }
}