package br.com.concrete.rodrigorocha.desafiokotlin.database

import org.jetbrains.exposed.dao.UUIDTable

internal object Users : UUIDTable() {

    val name = Users.varchar("name", 200)
    val email = Users.varchar("email", 200).uniqueIndex()
    val password = Users.varchar("password", 200)
    val created = Users.datetime("created")
    val modified = Users.datetime("modified")
    val lastLogin = Users.datetime("last_login")
    val token = Users.varchar("token", 300)
}