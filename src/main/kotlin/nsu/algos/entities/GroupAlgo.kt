package nsu.algos.entities

import jakarta.persistence.*

@Entity
@Table(name = "GROUP_ALGO")
class GroupAlgo (
    @Id
    @Column(name = "GROUP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "NAME")
    var name: String,

    @Column(name = "SIZE")
    val size: Int,
) {
    constructor(): this(
        0,"",0
    )
}