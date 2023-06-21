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
    val name: String,

    @Column(name = "TYPE")
    val type: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "TEACHER_ID_ALGO",
        nullable = true
    )
    val teacherId: TeacherAlgo,
)