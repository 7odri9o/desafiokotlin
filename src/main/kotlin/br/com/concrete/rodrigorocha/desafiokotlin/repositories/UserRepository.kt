package br.com.concrete.rodrigorocha.desafiokotlin.repositories

import br.com.concrete.rodrigorocha.desafiokotlin.database.Users
import br.com.concrete.rodrigorocha.desafiokotlin.domain.User
import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.*

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

    fun findByEmail(email: String): User? {
        return User.find { Users.email eq email }.firstOrNull()
    }

    fun findById(id: UUID): User? {
        return User.findById(id)
    }

    fun updateLastLogin(user: UserDTO) : Int {
        return Users.update({ Users.id eq user.id}) {
            it[lastLogin] = user.lastLogin!!
        }
    }
}