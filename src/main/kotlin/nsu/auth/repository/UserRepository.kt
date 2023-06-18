package nsu.auth.repository

import nsu.auth.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Long> {
    fun findByFullname(fullname: String): User?
    fun findByLogin(login: String): User?
    fun findByEmail(email: String): User?
    fun deleteByEmail(email: String)
    fun deleteByLogin(login: String)
    @Query("SELECT u FROM User u WHERE u.email LIKE %?1%")
    fun findAllByEmailLike(email: String): List<User>
    @Query("SELECT u FROM User u WHERE u.login LIKE %?1%")
    fun findAllByLoginLike(login: String): List<User>
}