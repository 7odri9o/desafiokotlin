package br.com.concrete.rodrigorocha.desafiokotlin.service

import br.com.concrete.rodrigorocha.desafiokotlin.domain.User
import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import br.com.concrete.rodrigorocha.desafiokotlin.repositories.PhoneRepository
import br.com.concrete.rodrigorocha.desafiokotlin.repositories.UserRepository
import br.com.concrete.rodrigorocha.desafiokotlin.security.JWTGenerator
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class UserService(private val userRepository: UserRepository,
                  private val phoneRepository: PhoneRepository
) {

    fun create(newUser: UserDTO) : UserDTO {

        val createDate = DateTime.now()
        newUser.created = createDate
        newUser.modified = createDate
        newUser.lastLogin = createDate


        val storedUser = transaction {
            val user = userRepository.save(newUser)
            user.token = JWTGenerator.sign(UserDTO(id = user.id.value), user.created, 30)
            user
        }

        newUser.phones?.forEach {
            phoneRepository.save(it, storedUser)
        }

        return toUserDTO(storedUser)
    }

//    private fun setFields(user: UserDTO) : UserDTO {
//        val now = getNow()
//        return user.copy(
//            token = JWTGenerator.sign(UserDTO(id = user.id), maxAgeInMinutes = 30),
//            created = now,
//            lastLogin = now,
//            modified = now)
//    }

    private fun toUserDTO(user: User) : UserDTO {

        val phones = phoneRepository.select(user)

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