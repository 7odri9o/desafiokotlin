package br.com.concrete.rodrigorocha.desafiokotlin.domain.repositories

import br.com.concrete.rodrigorocha.desafiokotlin.domain.database.Users
import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.User
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import javax.sql.DataSource

class UserRepository(private val dataSource: DataSource) {

    init {
        transaction(Database.connect(dataSource)) {
            SchemaUtils.create(Users)
        }
    }

    fun findByEmail(email: String): User? {
        return transaction(Database.connect(dataSource)) {
            Users.select { Users.email eq email }
                .firstOrNull()
                ?.let { Users.rowToUser(it) }
        }
    }

    fun create(user: User): User {
        return transaction {
            val id = Users.insertAndGetId { row ->
                row[Users.name] = user.name
                row[Users.email] = user.email
                row[Users.password] = user.password!!
            }.value

            val currentDate = Date()

            val test = user.copy(id = id,
                created = currentDate,
                modified = currentDate,
                last_login = currentDate,
                token = "token")
            test
        }
    }

}