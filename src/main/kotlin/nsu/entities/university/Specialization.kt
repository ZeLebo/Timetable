package nsu.entities.university

import javax.persistence.*

/**
 * Entity for specialization
 * @property studyYears - list of years (like courses) with lists of groups
 * @property name - name of the specialization
 * */

@Entity
@Table(name = "specialization")
class Specialization(
    @Column(name = "name")
    var name: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "faculty_id",
        nullable = true
    )
    var faculty: Faculty? = null,

    @OneToMany(mappedBy = "specialization")
    val studyYears: MutableList<StudyYear> = mutableListOf(),

    @Id
    @Column(name = "specialization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val specializationId: Long = 0,
)