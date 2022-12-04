package nsu.entities.university

import nsu.entities.people.Group
import javax.persistence.*

/**
 * Study Year (course) entity
 * @property subjects - list of the subjects during this year
 * @property year - course of studying
 * @property groups - list of groups on this course in faculty
 * */
@Entity
@Table(name = "study_year")
class StudyYear(
    @Column(name = "year")
    val year: Int,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "specialization_id",
        nullable = true
    )
    var specialization: Specialization? = null,

    @OneToMany(fetch = FetchType.LAZY)
    val groups: MutableList<Group> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY)
    val subjects: MutableList<Subject> = mutableListOf(),

    @Id
    @Column(name = "study_year_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val studyYearId: Long = 0,
)