package br.com.concrete.rodrigorocha.desafiokotlin.config

import io.javalin.Javalin
import org.koin.core.KoinProperties
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext

class ApplicationConfig : KoinComponent {

    fun setup() : Javalin {
        StandAloneContext.startKoin(listOf(),
            KoinProperties(true, true))
        return Javalin.create()
    }
}