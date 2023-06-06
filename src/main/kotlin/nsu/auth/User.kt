package nsu.auth

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority


@Entity
@Table(name = "users")
class User (
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long = 0,

    @Column(name = "fullname")
    var fullname: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "role")
    var roles: HashSet<Roles> = HashSet(),

    var password: String = "",
) {
    fun isAdmin(): Boolean {
        return this.roles.contains(Roles.ADMIN)
    }

    fun isTeacher(): Boolean {
        return this.roles.contains(Roles.TEACHER)
    }

    fun isStudent(): Boolean {
        return this.roles.contains(Roles.STUDENT)
    }

    fun getRoles(): Set<Roles> {
        return this.roles
    }

    fun getLogin(): String {
        return this.fullname
    }
}

enum class Roles: GrantedAuthority{
    STUDENT {
        override fun getAuthority(): String {
            return "STUDENT"
        }
    }, TEACHER {
        override fun getAuthority(): String {
            return "TEACHER"
        }
    }, ADMIN {
        override fun getAuthority(): String {
            return "ADMIN"
        }
    }
}
