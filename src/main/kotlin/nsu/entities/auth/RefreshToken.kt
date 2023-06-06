package nsu.entities.auth

import java.time.Clock
import java.time.Instant
import javax.persistence.*

// todo how does it work
@Entity
@Table(name = "refresh_tokens")
class RefreshToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "secret")
    val secret: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(name = "created")
    val created: Instant = Clock.systemDefaultZone().instant(),

    @Column(name = "last_used")
    val lastUsed: Instant = Clock.systemDefaultZone().instant(),

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    val status: EntityStatus = EntityStatus.ACTIVE,
)

enum class EntityStatus {
    ACTIVE, DELETED
}
