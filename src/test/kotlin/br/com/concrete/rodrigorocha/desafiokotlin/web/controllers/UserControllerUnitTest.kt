package br.com.concrete.rodrigorocha.desafiokotlin.web.controllers

import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.Phone
import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.User
import br.com.concrete.rodrigorocha.desafiokotlin.domain.service.UserService
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.UserRequestToUserConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.UserToUserResponseConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.PhoneRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserRequest
import io.javalin.BadRequestResponse
import io.javalin.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.eclipse.jetty.http.HttpStatus
import org.junit.Before
import org.junit.Test
import java.util.*

class UserControllerUnitTest {

    private lateinit var requestConverter: UserRequestToUserConverter
    private lateinit var responseConverter: UserToUserResponseConverter
    private lateinit var userService: UserService
    private lateinit var contextMock: Context

    @Before
    fun setup() {
        requestConverter = mockk(relaxed = true)
        responseConverter = mockk(relaxed = true)
        userService = mockk(relaxed = true)
        contextMock = mockk(relaxed = true)
    }
            
    @Test
    fun `deve retornar status code 201`() {

        val currentDate = Date()

        val user = User(
            1,
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(Phone(1, "11", "12345678")),
            currentDate,
            currentDate,
            currentDate,
            "token")

        val controller = UserController(requestConverter, responseConverter, userService)
        controller.register(contextMock)

        verify { contextMock.status(HttpStatus.CREATED_201)}
    }

    @Test(expected = BadRequestResponse::class)
    fun `deve retornar uma exception com a mensagem do nome`() {
        val email = "geraldo.azevedo"
        val password = "12345678"
        val phones = listOf(PhoneRequest("11", "12345678"))
        val userRequest = UserRequest("", email, password, phones)

        every { requestConverter.convert(contextMock) }.throws(BadRequestResponse("O Campo Name deve ser preenchido"))

        val controller = UserController(requestConverter, responseConverter, userService)
        controller.register(contextMock)
    }
}