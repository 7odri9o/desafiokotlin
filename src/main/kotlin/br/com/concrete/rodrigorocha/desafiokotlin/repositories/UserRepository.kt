package br.com.concrete.rodrigorocha.desafiokotlin.repositories

import br.com.concrete.rodrigorocha.desafiokotlin.domain.User
import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {

    fun save(user: UserDTO): User {
        return transaction {
            User.new {
                name = user.name.toString()
                email = user.email.toString()
                password = user.password.toString()
                created = user.created!!
                modified = user.modified!!
                lastLogin = user.lastLogin!!
                token = user.token!!
            }
        }
    }
}