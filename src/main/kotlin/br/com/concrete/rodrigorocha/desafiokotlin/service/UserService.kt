package br.com.concrete.rodrigorocha.desafiokotlin.service

import br.com.concrete.rodrigorocha.desafiokotlin.domain.PhoneDTO
import br.com.concrete.rodrigorocha.desafiokotlin.domain.User
import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import br.com.concrete.rodrigorocha.desafiokotlin.repositories.PhoneRepository
import br.com.concrete.rodrigorocha.desafiokotlin.repositories.UserRepository
import br.com.concrete.rodrigorocha.desafiokotlin.security.JWTGenerator
import br.com.concrete.rodrigorocha.desafiokotlin.util.getNow
import io.javalin.ConflictResponse
import org.jetbrains.exposed.sql.transactions.transaction

class UserService(private val userRepository: UserRepository,
                  private val phoneRepository: PhoneRepository) {

    fun create(newUser: UserDTO) : UserDTO {

        isEmailAlreadyUsed(newUser)
        val storedUser = saveUser(newUser)
        savePhones(newUser.phones, storedUser)
        return toUserDTO(storedUser)
    }

    private fun saveUser(newUser: UserDTO): User {
        return transaction {
            val user = userRepository.save(setDates(newUser))
            user.token = JWTGenerator.sign(UserDTO(id = user.id.value), user.created, 30)
            user
        }
    }

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

    private fun isEmailAlreadyUsed(user: UserDTO) {
        transaction {
            userRepository.countByEmail(user.email!!)
                .takeIf { it > 0 }?.apply {
                    throw ConflictResponse("Email já existente")
                }
        }
    }

    private fun savePhones(phones: List<PhoneDTO>?, owner: User) {
        phones?.forEach {
            phoneRepository.save(it, owner)
        }
    }

    private fun setDates(user: UserDTO) : UserDTO {
        val createDate = getNow()
        return user.copy(
            created = createDate,
            modified = createDate,
            lastLogin = createDate)
    }
}