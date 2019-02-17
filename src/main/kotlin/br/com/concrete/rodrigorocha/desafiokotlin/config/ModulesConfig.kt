package br.com.concrete.rodrigorocha.desafiokotlin.config

import br.com.concrete.rodrigorocha.desafiokotlin.repositories.PhoneRepository
import br.com.concrete.rodrigorocha.desafiokotlin.repositories.UserRepository
import br.com.concrete.rodrigorocha.desafiokotlin.service.LoginService
import br.com.concrete.rodrigorocha.desafiokotlin.service.UserService
import br.com.concrete.rodrigorocha.desafiokotlin.web.Router
import br.com.concrete.rodrigorocha.desafiokotlin.web.controllers.LoginController
import br.com.concrete.rodrigorocha.desafiokotlin.web.controllers.UserController
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.LoginRequestToLoginConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.NewUserToUserConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.UserToUserResponseConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.validators.ValidateFields
import org.koin.dsl.module.module

object ModulesConfig {

    private val configModule = module {
        single { ApplicationConfig() }
        single { Router(get(), get()) }
    }

    private val userModule = module {
        single { ValidateFields() }
        single { NewUserToUserConverter() }
        single { LoginRequestToLoginConverter(get()) }
        single { UserToUserResponseConverter() }
        single { UserRepository() }
        single { PhoneRepository() }
        single { UserService(get(), get()) }
        single { UserController(get(), get(), get()) }
        single { LoginService(get()) }
        single { LoginController(get(), get()) }
    }

    internal val allModules = listOf(
        ModulesConfig.configModule,
        ModulesConfig.userModule)
}