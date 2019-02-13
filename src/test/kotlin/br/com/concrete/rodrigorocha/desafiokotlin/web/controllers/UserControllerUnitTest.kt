package br.com.concrete.rodrigorocha.desafiokotlin.web.controllers

import br.com.concrete.rodrigorocha.desafiokotlin.service.UserService
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.UserRequestToUserConverter
import br.com.concrete.rodrigorocha.desafiokotlin.web.converters.UserToUserResponseConverter
import io.javalin.BadRequestResponse
import io.javalin.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.eclipse.jetty.http.HttpStatus
import org.junit.Before
import org.junit.Test

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

        val controller = UserController(requestConverter, responseConverter, userService)
        controller.register(contextMock)

        verify { contextMock.status(HttpStatus.CREATED_201)}
    }

    @Test(expected = BadRequestResponse::class)
    fun `deve retornar uma exception com a mensagem do nome`() {
        val email = "geraldo.azevedo"
        val password = "12345678"
        val userRequest = UserRequest("", email, password)

        every { requestConverter.convert(contextMock) }.throws(BadRequestResponse("O Campo Name deve ser preenchido"))

        val controller = UserController(requestConverter, responseConverter, userService)
        controller.register(contextMock)
    }
}