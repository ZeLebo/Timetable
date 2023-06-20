package nsu.entities.people

/**
 * Entity for teachers
 * */
import nsu.entities.university.Lesson
import jakarta.persistence.*
@Entity
@Table(name = "teacher")
class Teacher(
    @Column(name = "name")
    var name: String,

    @OneToMany(mappedBy = "teacher")
    var lessons: MutableList<Lesson> = mutableListOf(),

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val teacherId: Long = 0,
) {
    constructor(): this(
        ""
    )
}