package br.com.concrete.rodrigorocha.desafiokotlin.config

import br.com.concrete.rodrigorocha.desafiokotlin.config.ModulesConfig.allModules
import br.com.concrete.rodrigorocha.desafiokotlin.web.Router
import br.com.concrete.rodrigorocha.desafiokotlin.web.handlers.ErrorExceptionMapping
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.javalin.Javalin
import io.javalin.json.JavalinJackson
import org.koin.core.KoinProperties
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.getProperty
import org.koin.standalone.inject
import java.text.SimpleDateFormat

class ApplicationConfig : KoinComponent {

    private val router: Router by inject()

    fun setup() : Javalin {
        StandAloneContext.startKoin(allModules,
            KoinProperties(true, true))

        DatabaseFactory.init(getDBConfig())

        return Javalin.create()
            .also { app ->
                this.configureMapper()
                app.enableCorsForAllOrigins()
                    .contextPath(getProperty("context"))
                router.register(app)
                ErrorExceptionMapping.register(app)
                app.port(getProperty("server_port"))
            }
    }

    private fun getDBConfig() : HikariDataSource {
        val config = HikariConfig()
        config.jdbcUrl = getProperty("jdbc.url")
        config.username = getProperty("db.username")
        config.password = getProperty("db.password")
        config.validate()
        return HikariDataSource(config)
    }

    private fun configureMapper() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        JavalinJackson.configure(
            jacksonObjectMapper()
                .setDateFormat(dateFormat)
                .registerModule(JodaModule())
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS , false))

    }
}