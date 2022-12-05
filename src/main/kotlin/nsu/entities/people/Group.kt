package nsu.entities.people

import com.fasterxml.jackson.annotation.JsonIgnore
import nsu.entities.university.StudyYear
import javax.persistence.*

/**
 * Entity for group
 * */
@Entity
@Table(name = "groups")
class Group(
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val groupId: Long = 0,

    @Column(name = "number")
    var number: String,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
    var students: MutableList<Student> = mutableListOf(),

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "study_year_id",
        nullable = true
    )
    @field: JsonIgnore
    var studyYear: StudyYear? = null,
) {
    constructor(number: String, students: MutableList<Student>) : this(0, number, students)
}