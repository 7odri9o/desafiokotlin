package br.com.concrete.rodrigorocha.desafiokotlin.repositories

import br.com.concrete.rodrigorocha.desafiokotlin.domain.Phone
import br.com.concrete.rodrigorocha.desafiokotlin.domain.PhoneDTO
import br.com.concrete.rodrigorocha.desafiokotlin.domain.User
import org.jetbrains.exposed.sql.transactions.transaction

class PhoneRepository {

    fun save(phone: PhoneDTO, owner: User): Phone {
        return transaction {
            Phone.new {
                ddd = phone.ddd
                number = phone.number
                user = owner
            }
        }
    }
}