package nsu.entities.university

import jakarta.persistence.*

/**
 * Room entity
 * @property capacity - capacity of the room
 * @property roomType - type of the room (for lections, for seminars or for labs)
 * */
@Entity
@Table
class Room(
    @Column(name = "capacity")
    var capacity: Int,

    @Column(name = "room_type")
    var roomType: String,

    @Column(name = "number")
    var number: String,

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val roomId: Long = 0,
) {
    constructor(): this(
        0, RoomType.PRACTICE.name, ""
    )
}