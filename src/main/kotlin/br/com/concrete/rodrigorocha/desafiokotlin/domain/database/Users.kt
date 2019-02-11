package br.com.concrete.rodrigorocha.desafiokotlin.domain.database

import br.com.concrete.rodrigorocha.desafiokotlin.domain.dto.User
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.joda.time.DateTime

internal object Users: LongIdTable() {
    val name: Column<String> = Users.varchar("name", 200)
    val email: Column<String> = Users.varchar("email", 200).uniqueIndex()
    val password: Column<String> = Users.varchar("password", 20)
    val created: Column<DateTime> = Users.datetime("created")
    val modified = Users.datetime("modified")
    val last_login = Users.datetime("last_login")
    val token: Column<String> = Users.varchar("token", 200)

    fun rowToUser(row: ResultRow): User {
        return User(
            id = row[id].value,
            email = row[email],
            name = row[name],
            password = row[password],
            created = row[created],
            modified = row[modified],
            last_login = row[last_login],
            token = row[token])
    }
}