package br.com.concrete.rodrigorocha.desafiokotlin.web.converters

import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.PhoneRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserRequest
import br.com.concrete.rodrigorocha.desafiokotlin.web.validators.UserValidator
import io.javalin.Context
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserRequestToUserConverterTest {

    lateinit var userValidator: UserValidator
    lateinit var context: Context

    @Before
    fun setup() {
        userValidator = mockk(relaxed = true)
        context = mockk(relaxed = true)
    }

    @Test
    fun `deve retornar null na propriedade id do usuário`() {
        val converter = UserRequestToUserConverter(userValidator)

        val userRequest = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("11", "12345678")))

        every { userValidator.validateUserRequest(context) }.returns(userRequest)

        val user = converter.convert(context)

        Assert.assertNull(user.id)
    }

    @Test
    fun `deve retornar a string "Geraldo Azevedo" na propriedade name do usuário`() {
        val converter = UserRequestToUserConverter(userValidator)

        val userRequest = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("11", "12345678")))

        every { userValidator.validateUserRequest(context) }.returns(userRequest)

        val user = converter.convert(context)

        Assert.assertEquals("Geraldo Azevedo", user.name)
    }

    @Test
    fun `deve retornar a string do email na propriedade email do usuário`() {
        val converter = UserRequestToUserConverter(userValidator)

        val userRequest = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("11", "12345678")))

        every { userValidator.validateUserRequest(context) }.returns(userRequest)

        val user = converter.convert(context)

        Assert.assertEquals("geraldo.azevedo@gmail.com", user.email)
    }

    @Test
    fun `deve retornar a string da senha na propriedade password do usuário`() {
        val converter = UserRequestToUserConverter(userValidator)

        val userRequest = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("11", "12345678")))

        every { userValidator.validateUserRequest(context) }.returns(userRequest)

        val user = converter.convert(context)

        Assert.assertEquals("12345678", user.password)
    }

    @Test
    fun `deve retornar retornar um objeto do tipo phone na lista de telefones do usuário`() {
        val converter = UserRequestToUserConverter(userValidator)

        val userRequest = UserRequest(
            "Geraldo Azevedo",
            "geraldo.azevedo@gmail.com",
            "12345678",
            listOf(PhoneRequest("11", "12345678")))

        every { userValidator.validateUserRequest(context) }.returns(userRequest)

        val user = converter.convert(context)

        Assert.assertEquals(1, user.phones.size)
    }
}