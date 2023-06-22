package nsu.algos.entities

import jakarta.persistence.*

@Entity
@Table(name = "COURSE")
class Course(
    @Id
    @Column(name = "COURSE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(name = "NAME")
    var name: String,

    @Column(name = "TYPE")
    var type: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "TEACHER_ID_ALGO",
        nullable = true
    )
    val teacherId: TeacherAlgo,

    @Column(name = "COURSE")
    var groupId: Int
){
    constructor(): this(
        0,"","", TeacherAlgo(), 0
    )
}