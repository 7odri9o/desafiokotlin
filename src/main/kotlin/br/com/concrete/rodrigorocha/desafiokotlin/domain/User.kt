package br.com.concrete.rodrigorocha.desafiokotlin.domain

import br.com.concrete.rodrigorocha.desafiokotlin.database.Phones
import br.com.concrete.rodrigorocha.desafiokotlin.database.Users
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import java.util.*

class User(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, User>(Users)

    var name by Users.name
    var email by Users.email
    var password by Users.password
    var created by Users.created
    var modified by Users.modified
    var lastLogin by Users.lastLogin
    var token by Users.token
    val phones by Phone referrersOn Phones.user
}