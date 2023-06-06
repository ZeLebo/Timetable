package nsu.entities.auth

import javax.persistence.*

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
    var role: Roles,

    var password: String = "",

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    var userStatus: UserStatus = UserStatus.NOT_CONFIRMED

) {
    fun isAdmin(): Boolean {
       return this.role == Roles.ADMIN
    }

    fun isTeacher(): Boolean {
        return this.role == Roles.TEACHER
    }

    fun isStudent(): Boolean {
        return this.role == Roles.STUDENT
    }
}

enum class Roles {
    STUDENT, TEACHER, ADMIN
}

enum class UserStatus {
    CONFIRMED, NOT_CONFIRMED
}