package nsu.algos.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

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