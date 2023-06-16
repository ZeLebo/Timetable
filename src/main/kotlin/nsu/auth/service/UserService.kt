package nsu.auth.service

import nsu.auth.entity.Roles
import nsu.auth.entity.User
import nsu.auth.repository.UserRepository
import nsu.auth.requests.UserRequest
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashSet

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun addUser(user: User): User {
        if (userRepository.findByLogin(user.login) != null) {
            throw RuntimeException("Пользователь с таким логином уже существует")
        }
        return userRepository.save(user)
    }

    fun addUser(user: UserRequest): User {
        if (userRepository.findByLogin(user.login) != null) {
            throw RuntimeException("Пользователь с таким логином уже существует")
        }
        return userRepository.save(User(
            login = user.login,
            password = user.password,
            fullname = user.fullname,
            email = user.email,
            roles = HashSet(setOf(Roles.STUDENT))
        ))
    }

    fun addRolesToUserByLogin(login: String, roles: Set<Roles>): User {
        val user = userRepository.findByLogin(login) ?: throw RuntimeException("Пользователь не найден")
        user.roles.addAll(roles)
        return userRepository.save(user)
    }

    fun removeRolesFromUserByLogin(login: String, roles: Set<Roles>): User {
        val user = userRepository.findByLogin(login) ?: throw RuntimeException("Пользователь не найден")
        user.roles.removeAll(roles)
        return userRepository.save(user)
    }

    fun updateRolesForUserByLogin(login: String, roles: Set<Roles>): User {
        val user = userRepository.findByLogin(login) ?: throw RuntimeException("Пользователь не найден")
        user.roles = HashSet(roles)
        return userRepository.save(user)
    }

    fun deleteUserByLogin(login: String) {
        userRepository.deleteById(userRepository.findByLogin(login)?.userId ?: throw RuntimeException("Пользователь не найден"))
    }

    fun getByLogin(login: String): Optional<User> {
        return Optional.ofNullable(userRepository.findByLogin(login))
    }

    fun getByEmail(email: String): User {
        return userRepository.findByEmail(email) ?: throw RuntimeException("Пользователь не найден")
    }

    fun getByFullname(fullname: String): User {
        return userRepository.findByFullname(fullname) ?: throw RuntimeException("Пользователь не найден")
    }

    fun changePassword(user: User, newPassword: String) {
        user.password = newPassword
        userRepository.save(user)
    }

    fun changeFullname(user: User, newFullname: String) {
        user.fullname = newFullname
        userRepository.save(user)
    }

    fun changeEmail(user: User, newEmail: String) {
        user.email = newEmail
        userRepository.save(user)
    }

    fun addRole(user: User, role: Roles) {
        user.roles.add(role)
        userRepository.save(user)
    }
}