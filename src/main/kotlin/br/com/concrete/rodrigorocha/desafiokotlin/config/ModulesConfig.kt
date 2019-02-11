package br.com.concrete.rodrigorocha.desafiokotlin.config

import br.com.concrete.rodrigorocha.desafiokotlin.domain.repositories.UserRepository
import br.com.concrete.rodrigorocha.desafiokotlin.domain.service.UserService
import br.com.concrete.rodrigorocha.desafiokotlin.web.Router
import br.com.concrete.rodrigorocha.desafiokotlin.web.controllers.UserController
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.UserRequestToUserConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.UserToUserResponseConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.validators.UserValidator
import org.koin.dsl.module.module

object ModulesConfig {

    private val configModule = module {
        single { ApplicationConfig() }
        single { DbConfig(
            getProperty("jdbc.url"),
            getProperty("db.username"),
            getProperty("db.password")).getDataSource()
        }
        single { Router(get()) }
    }

    private val userModule = module {
        single { UserValidator() }
        single { UserRequestToUserConverter(get()) }
        single { UserToUserResponseConverter() }
        single { UserRepository(get()) }
        single { UserService(get()) }
        single { UserController(get(), get(), get()) }
    }

    internal val allModules = listOf(
        ModulesConfig.configModule,
        ModulesConfig.userModule)
}