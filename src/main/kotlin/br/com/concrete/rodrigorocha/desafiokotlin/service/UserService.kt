package br.com.concrete.rodrigorocha.desafiokotlin.service

import br.com.concrete.rodrigorocha.desafiokotlin.domain.PhoneDTO
import br.com.concrete.rodrigorocha.desafiokotlin.domain.User
import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import br.com.concrete.rodrigorocha.desafiokotlin.repositories.PhoneRepository
import br.com.concrete.rodrigorocha.desafiokotlin.repositories.UserRepository
import br.com.concrete.rodrigorocha.desafiokotlin.security.JWTGenerator
import br.com.concrete.rodrigorocha.desafiokotlin.util.getNow
import io.javalin.ConflictResponse
import io.javalin.UnauthorizedResponse
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.Minutes
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

class UserService(private val userRepository: UserRepository,
                  private val phoneRepository: PhoneRepository) {

    fun create(newUser: UserDTO) : UserDTO {

        isEmailAlreadyUsed(newUser)
        val storedUser = saveUser(newUser)
        savePhones(newUser.phones, storedUser)
        return toUserDTO(storedUser)
    }

    fun getUser(id: UUID, token: String): UserDTO {

        val user = transaction {
            userRepository.findById(id).let {

                toUserDTO(it)
            }
        }

        if (token != user.token) {
            throw UnauthorizedResponse("Não Autorizado")
        }

        val time = Minutes.minutesBetween(user.lastLogin, getNow()).minutes

        if (time > 1) {
            throw UnauthorizedResponse("Sessão Inválida")
        }

        return user
    }

    fun findUser(email: String): UserDTO? {
        return transaction {
            userRepository.findByEmail(email)?.let {
                toUserDTO(it)
            }
        }
    }

    fun updateLastLogin(user: UserDTO): UserDTO {
        return transaction {
            user.lastLogin = getNow()
            val updated = userRepository.updateLastLogin(user)
            if (updated != 1) null
            toUserDTO(userRepository.findById(user.id!!)!!)
        }
    }

    private fun saveUser(newUser: UserDTO): User {

        setPassword(newUser)

        return transaction {
            val user = userRepository.save(setDates(newUser))
            user.token = JWTGenerator.sign(UserDTO(id = user.id.value), user.created, 30)
            user
        }
    }

    private fun setPassword(newUser: UserDTO) {
        newUser.password = BCryptPasswordEncoder().encode(newUser.password)
    }

    private fun toUserDTO(user: User?) : UserDTO {

        if (user == null) throw IllegalArgumentException("User cannot be null")

        val phones = phoneRepository.select(user)

        return UserDTO(
            id = user.id.value,
            name = user.name,
            email = user.email,
            phones = phones,
            password = user.password,
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

        if (phones == null) throw IllegalArgumentException("Phones cannot be null")

        phones.forEach {
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