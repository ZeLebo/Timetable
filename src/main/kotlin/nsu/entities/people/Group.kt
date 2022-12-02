package nsu.entities.people

import javax.persistence.*
import nsu.entities.people.Student

/**
 * Entity for group
 * */
@Entity
@Table(name = "groups")
class Group(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val group_id: Long,

    @Column(name = "number")
    val number: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "students_to_group",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [JoinColumn(name = "group_id")]
    )
    val students: MutableList<Student> = mutableListOf()
)