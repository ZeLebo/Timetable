package nsu.entities.university

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

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
    @field: JsonIgnore
    var faculty: Faculty? = null,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "specialization")
    var studyYears: MutableList<StudyYear> = mutableListOf(),

    @Id
    @Column(name = "specialization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val specializationId: Long = 0,
)