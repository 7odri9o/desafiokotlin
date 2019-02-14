package br.com.concrete.rodrigorocha.desafiokotlin.service

import br.com.concrete.rodrigorocha.desafiokotlin.domain.PhoneDTO
import br.com.concrete.rodrigorocha.desafiokotlin.domain.User
import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import br.com.concrete.rodrigorocha.desafiokotlin.repositories.PhoneRepository
import br.com.concrete.rodrigorocha.desafiokotlin.repositories.UserRepository
import br.com.concrete.rodrigorocha.desafiokotlin.util.now
import org.jetbrains.exposed.sql.transactions.transaction

class UserService(private val userRepository: UserRepository,
                  private val phoneRepository: PhoneRepository
) {

    fun create(user: UserDTO) : UserDTO {

        val currentDate = now()

        val storedUser = userRepository.save(
            user.copy(
                token = "TOKEN",
                created = currentDate,
                lastLogin = currentDate,
                modified = currentDate
            )
        )

        user.phones?.forEach {
            phoneRepository.save(it, storedUser)
        }

        return toUserDTO(storedUser)

    }

    private fun toUserDTO(user: User) : UserDTO {

        val phones = transaction {
            user.phones.map { phone -> PhoneDTO(ddd = phone.ddd, number = phone.number) }
        }

        return UserDTO(
            id = user.id.value,
            name = user.name,
            email = user.email,
            phones = phones,
            created = user.created,
            modified = user.modified,
            lastLogin = user.lastLogin,
            token = user.token)
    }
}