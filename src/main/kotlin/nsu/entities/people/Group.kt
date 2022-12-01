package nsu.entities.people

import javax.persistence.*

/**
 * Entity for group
 * */
@Entity
@Table(name = "groups")
class Group(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "number")
    val number: String,

    // list of students in group
    @OneToMany(mappedBy = "group", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val students: List<Student> = emptyList()
)