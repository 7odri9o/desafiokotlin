package br.com.concrete.rodrigorocha.desafiokotlin.domain

import br.com.concrete.rodrigorocha.desafiokotlin.database.Phones
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID

class Phone(id: EntityID<Long>) : Entity<Long>(id) {
    companion object : EntityClass<Long, Phone>(Phones)
    var ddd by Phones.ddd
    var number by Phones.number
    var user by User referencedOn Phones.user
}