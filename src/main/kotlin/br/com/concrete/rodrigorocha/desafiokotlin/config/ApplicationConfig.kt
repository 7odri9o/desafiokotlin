package br.com.concrete.rodrigorocha.desafiokotlin.config

import br.com.concrete.rodrigorocha.desafiokotlin.config.ModulesConfig.allModules
import io.javalin.Javalin
import org.koin.core.KoinProperties
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext

class ApplicationConfig : KoinComponent {

    fun setup() : Javalin {
        StandAloneContext.startKoin(allModules,
            KoinProperties(true, true))
        return Javalin.create()
    }
}