package br.com.concrete.rodrigorocha.desafiokotlin.web.converters

import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO

class UserToUserResponseConverter {

    fun convert(user: UserDTO) : UserDTO {
        return UserDTO(
            id = user.id!!,
            created = user.created,
            modified = user.modified,
            lastLogin = user.lastLogin,
            password = user.password,
            phones = user.phones,
            token = user.token,
            name = user.name,
            email = user.email
        )
    }
}