package br.com.concrete.rodrigorocha.desafiokotlin.domain.service

import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.User
import java.util.*

class UserService {

    fun create(user: User) : User {
        val currentDate = Date()
        user.id = 1
        user.created = currentDate
        user.last_login = currentDate
        user.modified = currentDate
        user.token = "TOKEN"
        return user
    }
}