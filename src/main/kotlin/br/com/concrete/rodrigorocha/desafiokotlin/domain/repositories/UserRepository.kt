package br.com.concrete.rodrigorocha.desafiokotlin.domain.repositories

import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.User
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

internal object Users: LongIdTable() {
    val name: Column<String> = varchar("name", 200)
    val email: Column<String> = varchar("email", 200).uniqueIndex()
    val password: Column<String> = varchar("password", 20)

    fun rowToUser(row: ResultRow): User {
        return User(
            id = row[Users.id].value,
            email = row[Users.email],
            name = row[Users.name]
        )
    }
}