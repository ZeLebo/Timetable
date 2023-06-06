package nsu.auth

import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashSet

@Service
class UserService() {
    val users: MutableList<User> = listOf(
        User(1, "student", "student", HashSet(setOf(Roles.STUDENT)), "a"),
        User(2, "admin", "admin", HashSet(setOf(Roles.ADMIN, Roles.TEACHER, Roles.STUDENT)), "a"),
        User(3, "teacher", "teacher", HashSet(setOf(Roles.TEACHER, Roles.STUDENT)), "a"),
        ).toMutableList()

    fun getByLogin(login: String): Optional<User> {
        return Optional.of(users.filter {
            it.fullname == login
        }[0])
    }


}