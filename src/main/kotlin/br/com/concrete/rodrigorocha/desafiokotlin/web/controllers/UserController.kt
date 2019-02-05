package br.com.concrete.rodrigorocha.desafiokotlin.web.controllers

import br.com.concrete.rodrigorocha.desafiokotlin.domain.service.UserService
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.UserRequestToUserConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.UserToUserResponseConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserRequest
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class UserController(
    private val userRequestToUserConverter: UserRequestToUserConverter,
    private val userToUserResponseConverter: UserToUserResponseConverter,
    private val userService: UserService) {

    fun register(ctx: Context) {
        val requestBody = ctx.body<UserRequest>()
        userService.create(userRequestToUserConverter.convert(requestBody))
            .apply {
                ctx.status(HttpStatus.CREATED_201)
                ctx.json(userToUserResponseConverter.convert(this))
            }
    }
}