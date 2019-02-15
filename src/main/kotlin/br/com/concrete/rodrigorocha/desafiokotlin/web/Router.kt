package br.com.concrete.rodrigorocha.desafiokotlin.web

import br.com.concrete.rodrigorocha.desafiokotlin.web.controllers.LoginController
import br.com.concrete.rodrigorocha.desafiokotlin.web.controllers.UserController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.koin.standalone.KoinComponent

class Router(
    private val userController: UserController,
    private val loginController: LoginController) : KoinComponent {

    fun register(app: Javalin) {
        app.routes {
            path("users") {
                get(":id", userController::getUser)
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