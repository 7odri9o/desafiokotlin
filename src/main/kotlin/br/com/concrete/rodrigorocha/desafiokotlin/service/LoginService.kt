package br.com.concrete.rodrigorocha.desafiokotlin.service

import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.LoginDTO
import io.javalin.UnauthorizedResponse

class LoginService(private val userService: UserService) {

    fun login(loginDTO: LoginDTO): UserDTO {
        val user = findUser(loginDTO)
        return user
    }

    private fun findUser(loginDTO: LoginDTO): UserDTO {

        return userService.findUser(loginDTO.email!!) ?:
            throw UnauthorizedResponse("Usuário e/ou senha inválidos")
    }
}