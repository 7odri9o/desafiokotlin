package br.com.concrete.rodrigorocha.desafiokotlin.domain.service

import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.User
import br.com.concrete.rodrigorocha.desafiokotlin.domain.repositories.UserRepository
import io.javalin.HttpResponseException
import org.eclipse.jetty.http.HttpStatus
import org.joda.time.DateTime

class UserService(private val userRepository: UserRepository) {

    fun create(user: User) : User {
        userRepository.findByEmail(user.email).takeIf {
            it != null
        }?.apply {
            throw HttpResponseException(
                HttpStatus.BAD_REQUEST_400,
                "Email já cadastrado") }

        val currentDateTime = DateTime()

        return userRepository.create(
            user.copy(
                created = currentDateTime,
                modified = currentDateTime,
                last_login = currentDateTime,
                token = "TOKEN"
            )
        )
    }
}