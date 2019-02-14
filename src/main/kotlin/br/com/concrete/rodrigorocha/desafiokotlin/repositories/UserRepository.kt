package br.com.concrete.rodrigorocha.desafiokotlin.repositories

import br.com.concrete.rodrigorocha.desafiokotlin.database.Users
import br.com.concrete.rodrigorocha.desafiokotlin.domain.User
import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import org.jetbrains.exposed.sql.select
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
            }
        }
    }

    fun countByEmail(email: String): Int {
        return Users.select { Users.email eq email }.count()
    }
}