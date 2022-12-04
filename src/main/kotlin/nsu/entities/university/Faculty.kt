package nsu.entities.university

import javax.persistence.*

/**
 * @property specializations - list of specializations on this faculty.
 * @property name - faculty name
 * */
@Entity
@Table(name = "faculty")
class Faculty(
    @Column(name = "name")
    var name: String,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "faculty")
    var specializations: MutableList<Specialization> = mutableListOf(),

    @Id
    @Column(name = "faculty_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val facultyId: Long = 0,
)