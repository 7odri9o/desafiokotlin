package br.com.concrete.rodrigorocha.desafiokotlin.config

import br.com.concrete.rodrigorocha.desafiokotlin.database.Phones
import br.com.concrete.rodrigorocha.desafiokotlin.database.Users
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    private val schemas : Array<Table> = arrayOf(Users, Phones)

    fun init(dataSource: HikariDataSource) {
        Database.connect(dataSource)
        transaction {
            SchemaUtils.create(*schemas)
        }
    }
}