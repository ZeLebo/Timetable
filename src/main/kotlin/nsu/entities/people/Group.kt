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

    @Column(name = "number")
    var number: String,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
    var students: MutableList<Student> = mutableListOf(),

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val groupId: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "study_year_id",
        nullable = true
    )
    @field: JsonIgnore
    var studyYear: StudyYear? = null,
) {
    constructor(number: String, students: MutableList<Student>) : this(number, students, 0, null)
}