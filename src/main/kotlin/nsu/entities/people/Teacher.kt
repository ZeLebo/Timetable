package nsu.entities.people

import nsu.entities.university.Subject
import javax.persistence.*
@Entity
@Table(name = "teacher")
class Teacher(
    @Column(name = "name")
    val name: String,

    @OneToMany(mappedBy = "teacher")
    val subjects: MutableList<Subject>,

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val teacherId: Long = 0,
)