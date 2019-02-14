package br.com.concrete.rodrigorocha.desafiokotlin.web.controllers

import br.com.concrete.rodrigorocha.desafiokotlin.service.LoginService
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.LoginRequestToLoginConverter
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class LoginController(
    private val loginRequestToLoginConverter : LoginRequestToLoginConverter,
    private val loginService: LoginService) {

    fun login(ctx: Context) {

        val request = loginRequestToLoginConverter.convert(ctx)
        ctx.status(HttpStatus.OK_200)
        val user = loginService.login(request)
        ctx.json(user)
    }
}