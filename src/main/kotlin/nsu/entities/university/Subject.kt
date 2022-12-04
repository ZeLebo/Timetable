package nsu.entities.university

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
    val name: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "study_year_id",
        nullable = true
    )
    var StudyYear: StudyYear? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "teacher_id",
        nullable = true
    )
    var teacher: Teacher? = null,

    @OneToMany(mappedBy = "subject")
    val lessons: MutableList<Lesson> = mutableListOf(),

    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val subjectId: Long = 0,
)