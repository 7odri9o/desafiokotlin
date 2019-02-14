package br.com.concrete.rodrigorocha.desafiokotlin.service

import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.LoginDTO
import io.javalin.UnauthorizedResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class LoginService(private val userService: UserService) {

    fun login(loginDTO: LoginDTO): UserDTO {
        val user = findUser(loginDTO)

        isCorrectPassword(loginDTO.password, user.password)

        return userService.updateLastLogin(user)
    }

    private fun isCorrectPassword(rawPassword: String?, encryptedPassword: String?): Boolean {
        val itMatches = BCryptPasswordEncoder().matches(rawPassword, encryptedPassword)
        if(!itMatches) throw UnauthorizedResponse("Usuário e/ou senha inválidos")
        return itMatches
    }

    private fun findUser(loginDTO: LoginDTO): UserDTO {

        return userService.findUser(loginDTO.email!!) ?:
            throw UnauthorizedResponse("Usuário e/ou senha inválidos")
    }
}