package br.com.concrete.rodrigorocha.desafiokotlin.service

import br.com.concrete.rodrigorocha.desafiokotlin.domain.User
import br.com.concrete.rodrigorocha.desafiokotlin.domain.UserDTO
import br.com.concrete.rodrigorocha.desafiokotlin.repositories.PhoneRepository
import br.com.concrete.rodrigorocha.desafiokotlin.repositories.UserRepository
import org.joda.time.DateTime

class UserService(private val userRepository: UserRepository,
                  private val phoneRepository: PhoneRepository
) {

    fun create(user: UserDTO) : UserDTO {

        val newUser = generateToken(setDates(user))
        val storedUser = userRepository.save(newUser)

        newUser.phones?.forEach {
            phoneRepository.save(it, storedUser)
        }

        return toUserDTO(storedUser)

    }

    private fun setDates(user: UserDTO) : UserDTO {

        val now = DateTime.now()

        return user.copy(
            created = now,
            modified = now,
            lastLogin = now)
    }

    private fun generateToken(user: UserDTO) : UserDTO {
        return user.copy(token = "TOKEN")
    }

    private fun toUserDTO(user: User) : UserDTO {

        return UserDTO(
            id = user.id.value,
            name = user.name,
            email = user.email,
            password = user.password,
            created = user.created,
            modified = user.modified,
            lastLogin = user.lastLogin,
            token = user.token)
    }
}