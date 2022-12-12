package nsu.entities.university

import com.fasterxml.jackson.annotation.JsonIgnore
import nsu.entities.people.Group
import org.hibernate.annotations.Fetch
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
    var year: Int,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "specialization_id",
        nullable = true
    )
    @field: JsonIgnore
    var specialization: Specialization? = null,

    @Column(name = "specialization_name")
    var specializationName: String? = null,

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    var groups: MutableList<Group> = mutableListOf(),

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    var subjects: MutableList<Subject> = mutableListOf(),

    @Id
    @Column(name = "study_year_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val studyYearId: Long = 0,
)