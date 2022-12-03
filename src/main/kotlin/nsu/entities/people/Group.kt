package nsu.entities.people

import javax.persistence.*

/**
 * Entity for group
 * */
@Entity
@Table(name = "groups")
class Group(
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val groupId: Long = 0,

    @Column(name = "number")
    val number: String,

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//        name = "students_to_group",
//        joinColumns = [JoinColumn(name = "student_id")],
//        inverseJoinColumns = [JoinColumn(name = "group_id")]
//    )
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
//    @JoinColumn(name = "student_id")
    val students: MutableList<Student> = mutableListOf()
) {
    constructor(number: String, students: MutableList<Student>) : this(0, number, students)
}