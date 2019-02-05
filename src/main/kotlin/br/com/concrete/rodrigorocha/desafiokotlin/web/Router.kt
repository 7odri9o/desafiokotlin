package br.com.concrete.rodrigorocha.desafiokotlin.web

import br.com.concrete.rodrigorocha.desafiokotlin.web.controllers.UserController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import org.koin.standalone.KoinComponent

class Router(
    private val userController: UserController) : KoinComponent {

    fun register(app: Javalin) {
        app.routes {
            path("users") {
                post(userController::register)
            }
        }
    }


}