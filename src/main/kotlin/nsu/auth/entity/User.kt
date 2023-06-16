package nsu.auth.entity

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
    var fullname: String = "",

    @Column
    val login: String = "",

    @Column(name = "email")
    var email: String = "",

    @Column(name = "roles")
    var roles: HashSet<Roles> = HashSet(setOf(Roles.STUDENT)),

    var password: String = ""
) {

    fun getRoles(): Set<Roles> {
        return this.roles
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
