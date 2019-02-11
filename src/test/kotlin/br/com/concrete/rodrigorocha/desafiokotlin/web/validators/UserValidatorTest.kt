package br.com.concrete.rodrigorocha.desafiokotlin.web.validators

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserRequest
import io.javalin.BadRequestResponse
import io.javalin.Context
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserValidatorTest {

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
            "12345678")

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
            "12345678")

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
            "12345678")

        every { context.body<UserRequest>() }.returns(userRequestStub)

        val userRequest = validator.validateUserRequest(context)

        Assert.assertEquals("12345678", userRequest.password)
    }

    @Test(expected = BadRequestResponse::class)
    fun `quando o campo name estiver vazio retornar a BadRequestResponse Exception`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "",
            "geraldo.azevedo@gmail.com",
            "12345678")

        every { context.body<UserRequest>() }.returns(userRequestStub)

        validator.validateUserRequest(context)
    }

    @Test(expected = BadRequestResponse::class)
    fun `quando o campo email estiver vazio retornar a BadRequestResponse Exception`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "Geraldo Azevedo",
            "",
            "12345678")

        every { context.body<UserRequest>() }.returns(userRequestStub)

        validator.validateUserRequest(context)
    }

    @Test(expected = BadRequestResponse::class)
    fun `quando o campo password estiver vazio retornar a BadRequestResponse Exception`() {
        val validator = UserValidator()

        val userRequestStub = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "")

        every { context.body<UserRequest>() }.returns(userRequestStub)

        validator.validateUserRequest(context)
    }
}