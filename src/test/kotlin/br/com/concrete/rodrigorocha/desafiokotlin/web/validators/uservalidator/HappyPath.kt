package br.com.concrete.rodrigorocha.desafiokotlin.web.validators.uservalidator

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.PhoneRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.validators.UserValidator
import io.javalin.Context
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class HappyPath {

    lateinit var context: Context

    @Before
    fun setup() {
        context = mockk(relaxed = true)
    }

    @Test
    fun `deve retornar um objeto do tipo UserRequest contendo a propriedade name com o valor esperado`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("11", "12345678")))

        every { context.body<UserRequest>() }.returns(userRequestStub)

        val userRequest = validator.validateUserRequest(context)

        Assert.assertEquals("Geraldo Azevedo", userRequest.name)
    }

    @Test
    fun `deve retornar um objeto do tipo UserRequest contendo a propriedade email com o valor esperado`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("11", "12345678")))

        every { context.body<UserRequest>() }.returns(userRequestStub)

        val userRequest = validator.validateUserRequest(context)

        Assert.assertEquals("geraldo.azevedo@gmail.com", userRequest.email)
    }

    @Test
    fun `deve retornar um objeto do tipo UserRequest contendo a propriedade password com o valor esperado`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("11", "12345678")))

        every { context.body<UserRequest>() }.returns(userRequestStub)

        val userRequest = validator.validateUserRequest(context)

        Assert.assertEquals("12345678", userRequest.password)
    }

    @Test
    fun `deve retornar um objeto do tipo UserRequest contendo a propriedade phones com valor diferente de null`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("11", "12345678")))

        every { context.body<UserRequest>() }.returns(userRequestStub)

        val userRequest = validator.validateUserRequest(context)

        Assert.assertNotNull(userRequest.phones)
    }

    @Test
    fun `deve retornar um objeto do tipo UserRequest contendo a propriedade phones com tamanho 1`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("11", "12345678")))

        every { context.body<UserRequest>() }.returns(userRequestStub)

        val userRequest = validator.validateUserRequest(context)

        Assert.assertEquals(1, userRequest.phones!!.size)
    }
}