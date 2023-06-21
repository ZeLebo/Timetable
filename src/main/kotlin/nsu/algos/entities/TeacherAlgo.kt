package nsu.algos.entities

import jakarta.persistence.*

@Entity
@Table(name = "TEACHER_ALGO")
class TeacherAlgo (
    @Id
    @Column(name = "TEACHER_ID_ALGO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(name = "NAME")
    val name: String
)