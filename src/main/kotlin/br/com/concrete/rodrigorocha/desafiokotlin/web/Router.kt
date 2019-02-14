package br.com.concrete.rodrigorocha.desafiokotlin.web

import br.com.concrete.rodrigorocha.desafiokotlin.web.controllers.LoginController
import br.com.concrete.rodrigorocha.desafiokotlin.web.controllers.UserController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import org.koin.standalone.KoinComponent

class Router(
    private val userController: UserController,
    private val loginController: LoginController) : KoinComponent {

    fun register(app: Javalin) {
        app.routes {
            path("users") {
                post(userController::register)
            }
        }

        app.routes {
            path("login") {
                post(loginController::login)
            }
        }
    }


}