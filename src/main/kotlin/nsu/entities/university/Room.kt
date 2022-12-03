package nsu.entities.university

import javax.persistence.*

/**
 * Room entity
 * @property capacity - capacity of the room
 * @property roomType - type of the room (for lections, for seminars or for labs)
 * */
@Entity
@Table
class Room(
    @Column(name = "capacity")
    val capacity: Int,

    @Column(name = "room_type")
    val roomType: String,

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val roomId: Long = 0,
)