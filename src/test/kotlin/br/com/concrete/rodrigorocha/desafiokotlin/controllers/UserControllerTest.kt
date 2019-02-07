package br.com.concrete.rodrigorocha.desafiokotlin.controllers

import br.com.concrete.rodrigorocha.desafiokotlin.config.ApplicationConfig
import br.com.concrete.rodrigorocha.desafiokotlin.util.HttpUtil
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.PhoneRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserResponse
import br.com.concrete.rodrigorocha.desafiokotlin.web.handlers.ErrorResponse
import com.mashape.unirest.http.HttpResponse
import io.javalin.Javalin
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

class UserControllerTest {

    companion object {

        private lateinit var app: Javalin
        private lateinit var http: HttpUtil

        @BeforeClass @JvmStatic
        fun setup() {
            app = ApplicationConfig().setup().start()
            http = HttpUtil(app.port())
        }

        @AfterClass
        @JvmStatic
        fun stop() {
            app.stop()
        }
    }

    @Test
    fun `deve registrar um usuário com sucesso`() {
        val name = "Geraldo Azevedo"
        val email = "geraldo.azevedo@gmail.com"
        val password = "12345678"
        val phones = listOf(PhoneRequest("11", "12345678"))
        val userRequest = UserRequest(name, email, password, phones)

        val response: HttpResponse<UserResponse> = http.post("/api/users", userRequest)

        assertEquals(201, response.status)
    }

    @Test
    fun `deve retornar a string "Geraldo Azevedo" no campo nome`() {
        val name = "Geraldo Azevedo"
        val email = "geraldo.azevedo@gmail.com"
        val password = "12345678"
        val phones = listOf(PhoneRequest("11", "12345678"))
        val userRequest = UserRequest(name, email, password, phones)

        val response: HttpResponse<UserResponse> = http.post("/api/users", userRequest)

        assertEquals("Geraldo Azevedo", response.body.name)
    }

    @Test
    fun `deve retornar a string o valor da variável email no campo email`() {
        val name = "Geraldo Azevedo"
        val email = "geraldo.azevedo@gmail.com"
        val password = "12345678"
        val phones = listOf(PhoneRequest("11", "12345678"))
        val userRequest = UserRequest(name, email, password, phones)

        val response: HttpResponse<UserResponse> = http.post("/api/users", userRequest)

        assertEquals("geraldo.azevedo@gmail.com", response.body.email)
    }

    @Test
    fun `deve retornar a propriedade id no response`() {
        val name = "Geraldo Azevedo"
        val email = "geraldo.azevedo@gmail.com"
        val password = "12345678"
        val phones = listOf(PhoneRequest("11", "12345678"))
        val userRequest = UserRequest(name, email, password, phones)

        val response: HttpResponse<UserResponse> = http.post("/api/users", userRequest)

        assertNotNull(response.body.id)
    }

    @Test
    fun `deve retornar a propriedade created no response`() {
        val name = "Geraldo Azevedo"
        val email = "geraldo.azevedo@gmail.com"
        val password = "12345678"
        val phones = listOf(PhoneRequest("11", "12345678"))
        val userRequest = UserRequest(name, email, password, phones)

        val response: HttpResponse<UserResponse> = http.post("/api/users", userRequest)

        assertNotNull(response.body.created)
    }

    @Test
    fun `deve retornar a propriedade modified no response`() {
        val name = "Geraldo Azevedo"
        val email = "geraldo.azevedo@gmail.com"
        val password = "12345678"
        val phones = listOf(PhoneRequest("11", "12345678"))
        val userRequest = UserRequest(name, email, password, phones)

        val response: HttpResponse<UserResponse> = http.post("/api/users", userRequest)

        assertNotNull(response.body.modified)
    }

    @Test
    fun `deve retornar a propriedade last_login no response`() {
        val name = "Geraldo Azevedo"
        val email = "geraldo.azevedo@gmail.com"
        val password = "12345678"
        val phones = listOf(PhoneRequest("11", "12345678"))
        val userRequest = UserRequest(name, email, password, phones)

        val response: HttpResponse<UserResponse> = http.post("/api/users", userRequest)

        assertNotNull(response.body.last_login)
    }

    @Test
    fun `deve retornar a propriedade token no response`() {
        val name = "Geraldo Azevedo"
        val email = "geraldo.azevedo@gmail.com"
        val password = "12345678"
        val phones = listOf(PhoneRequest("11", "12345678"))
        val userRequest = UserRequest(name, email, password, phones)

        val response: HttpResponse<UserResponse> = http.post("/api/users", userRequest)

        assertNotNull(response.body)
    }

    @Test
    fun `deve retornar erro ao enviar email inválido`() {
        val name = "Geraldo Azevedo"
        val email = "geraldo.azevedo"
        val password = "12345678"
        val phones = listOf(PhoneRequest("11", "12345678"))
        val userRequest = UserRequest(name, email, password, phones)

        val response: HttpResponse<ErrorResponse> = http.post("/api/users", userRequest)

        assertEquals("Formato de email inválido", response.body.message)
    }
}
