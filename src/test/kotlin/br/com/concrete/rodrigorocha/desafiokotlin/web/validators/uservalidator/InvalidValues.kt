package br.com.concrete.rodrigorocha.desafiokotlin.web.validators.uservalidator

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.PhoneRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.validators.UserValidator
import io.javalin.BadRequestResponse
import io.javalin.Context
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class InvalidValues {

    lateinit var context: Context

    @Before
    fun setup() {
        context = mockk(relaxed = true)
    }

    @Test(expected = BadRequestResponse::class)
    fun `quando o campo name estiver vazio retornar a BadRequestResponse Exception`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("11", "12345678")))

        every { context.body<UserRequest>() }.returns(userRequestStub)

        validator.validateUserRequest(context)
    }

    @Test(expected = BadRequestResponse::class)
    fun `quando o campo email estiver vazio retornar a BadRequestResponse Exception`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "Geraldo Azevedo",
            "",
            "12345678",
            listOf(PhoneRequest("11", "12345678")))

        every { context.body<UserRequest>() }.returns(userRequestStub)

        validator.validateUserRequest(context)
    }

    @Test(expected = BadRequestResponse::class)
    fun `quando o campo password estiver vazio retornar a BadRequestResponse Exception`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "",
            listOf(PhoneRequest("11", "12345678")))

        every { context.body<UserRequest>() }.returns(userRequestStub)

        validator.validateUserRequest(context)
    }

    @Test(expected = BadRequestResponse::class)
    fun `quando o campo ddd estiver vazio retornar a BadRequestResponse Exception`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("", "12345678")))

        every { context.body<UserRequest>() }.returns(userRequestStub)

        validator.validateUserRequest(context)
    }

    @Test(expected = BadRequestResponse::class)
    fun `quando o campo number estiver vazio retornar a BadRequestResponse Exception`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("11", "")))

        every { context.body<UserRequest>() }.returns(userRequestStub)

        validator.validateUserRequest(context)
    }
}