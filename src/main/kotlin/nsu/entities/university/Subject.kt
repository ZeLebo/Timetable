package nsu.entities.university

import com.fasterxml.jackson.annotation.JsonIgnore
import nsu.entities.people.Teacher
import javax.persistence.*

/**
 * Subject entity
 * @property lessons - list of lessons for this subject (like amount of study hours for this subject)
 * @property name - name of the subject
 * */
@Entity
@Table(name = "subject")
class Subject(
    @Column(name = "name")
    var name: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "study_year_id",
        nullable = true
    )
    @field: JsonIgnore
    var StudyYear: StudyYear? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "teacher_id",
        nullable = true
    )
    @field: JsonIgnore
    var teacher: Teacher? = null,

    @OneToMany(mappedBy = "subject")
    var lessons: MutableList<Lesson> = mutableListOf(),

    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val subjectId: Long = 0,
)