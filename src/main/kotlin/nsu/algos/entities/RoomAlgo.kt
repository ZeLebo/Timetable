package nsu.algos.entities

import jakarta.persistence.*

@Entity
@Table(name = "ROOM_ALGO")
class RoomAlgo(
    @Id
    @Column(name = "ROOM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "NAME")
    var name: String,

    @Column(name = "CAPACITY")
    var capacity: Int
)