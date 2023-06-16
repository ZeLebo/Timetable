package nsu.auth.repository

import nsu.auth.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByFullname(fullname: String): User?
    fun findByLogin(login: String): User?
    fun findByEmail(email: String): User?
    fun deleteByEmail(email: String)
    fun deleteByLogin(login: String)
}