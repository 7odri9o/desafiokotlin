package br.com.concrete.rodrigorocha.desafiokotlin.config

import org.koin.dsl.module.module

object ModulesConfig {

    private val configModule = module {
        single { ApplicationConfig() }
    }

    internal val allModules = listOf(ModulesConfig.configModule)
}