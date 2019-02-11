package br.com.concrete.rodrigorocha.desafiokotlin.domain.service

import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.User
import br.com.concrete.rodrigorocha.desafiokotlin.domain.repositories.UserRepository
import org.eclipse.jetty.http.HttpStatus

class UserService(private val userRepository: UserRepository) {

    fun create(user: User) : User {
        userRepository.findByEmail(user.email).takeIf {
            it != null
        }?.apply {
            throw io.javalin.HttpResponseException(
                HttpStatus.BAD_REQUEST_400,
                "Email já cadastrado") }

        return userRepository.create(User(null, user.name, user.email, user.password ))
    }
}