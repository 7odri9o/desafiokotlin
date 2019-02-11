package br.com.concrete.rodrigorocha.desafiokotlin.domain.database

import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.User
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

internal object Users: LongIdTable() {
    val name: Column<String> = Users.varchar("name", 200)
    val email: Column<String> = Users.varchar("email", 200).uniqueIndex()
    val password: Column<String> = Users.varchar("password", 20)
    
    fun rowToUser(row: ResultRow): User {
        return User(
            id = row[id].value,
            email = row[email],
            name = row[name],
            password = row[password])
    }
}