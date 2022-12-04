package nsu.entities.university

import javax.persistence.*

/**
 * @property specializations - list of specializations on this faculty.
 * @property name - faculty name
 * */
data class Faculty2(val specializations: ArrayList<Specialization>, val name: String)

@Entity
@Table(name = "faculty")
class Faculty(
    @Column(name = "name")
    val name: String,

    @OneToMany(mappedBy = "faculty")
    val specializations: MutableList<Specialization> = mutableListOf(),

    @Id
    @Column(name = "faculty_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val facultyId: Long = 0,
)