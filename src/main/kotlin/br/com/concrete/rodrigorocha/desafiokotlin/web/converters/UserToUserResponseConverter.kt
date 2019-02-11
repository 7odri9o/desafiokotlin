package br.com.concrete.rodrigorocha.desafiokotlin.web.converters

import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.User
import br.com.concrete.rodrigorocha.desafiokotlin.web.dto.UserResponse

class UserToUserResponseConverter {

    fun convert(user: User) : UserResponse {
        return UserResponse(
            user.id!!,
            user.created.toString(),
            user.modified.toString(),
            user.last_login.toString(),
            user.token!!,
            user.name,
            user.email)
    }
}