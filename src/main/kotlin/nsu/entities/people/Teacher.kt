package nsu.entities.people

import nsu.entities.university.Lesson
import javax.persistence.*
@Entity
@Table(name = "teacher")
class Teacher(
    @Column(name = "name")
    var name: String,

    @OneToMany(mappedBy = "teacher")
    var lessons: MutableList<Lesson>,

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val teacherId: Long = 0,
)