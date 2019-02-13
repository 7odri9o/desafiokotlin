package br.com.concrete.rodrigorocha.desafiokotlin.database

import org.jetbrains.exposed.dao.LongIdTable

internal object Phones : LongIdTable() {

    val ddd = Phones.varchar("ddd", 2)
    val number = Phones.varchar("number", 9)
    var user = reference("user", Users)

}