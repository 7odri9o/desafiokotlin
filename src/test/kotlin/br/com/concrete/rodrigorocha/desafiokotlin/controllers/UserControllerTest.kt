package br.com.concrete.rodrigorocha.desafiokotlin.controllers

import br.com.concrete.rodrigorocha.desafiokotlin.config.ApplicationConfig
import br.com.concrete.rodrigorocha.desafiokotlin.util.HttpUtil
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.PhoneRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserResponse
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.ObjectMapper
import com.mashape.unirest.http.Unirest.setObjectMapper
import io.javalin.Javalin
import io.javalin.json.JavalinJson
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserControllerTest {

    private lateinit var app: Javalin
    private lateinit var http: HttpUtil

    @Before
    fun start() {
        app = ApplicationConfig().setup().start()
        http = HttpUtil(app.port())
        setObjectMapper(object : ObjectMapper {

            override fun <T> readValue(value: String, valueType: Class<T>): T {
                return JavalinJson.fromJson(value, valueType)
            }

            override fun writeValue(value: Any): String {
                return JavalinJson.toJson(value)
            }
        })
    }

    @After
    fun stop() {
        app.stop()
    }

    @Test
    fun `deve registrar um usuário com sucesso`() {
        val name = "Geraldo Azevedo"
        val email = "geraldo.azevedo@gmail.com"
        val password = "12345678"
        val phones = listOf(PhoneRequest("11", "12345678"))
        val userRequest = UserRequest(name, email, password, phones)

        val response = http.post("/api/users", userRequest)

        assertEquals(201, response.status)
    }

    @Test
    fun `deve retornar o "Geraldo Azevedo" no campo nome`() {
        val name = "Geraldo Azevedo"
        val email = "geraldo.azevedo@gmail.com"
        val password = "12345678"
        val phones = listOf(PhoneRequest("11", "12345678"))
        val userRequest = UserRequest(name, email, password, phones)

        val response: HttpResponse<UserResponse> = http.post("/api/users", userRequest)

        assertEquals("Geraldo Azevedo", response.body.name)
    }
}