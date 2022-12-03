package nsu.entities.university

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

    @OneToMany(mappedBy = "subject")
    val lessons: MutableList<Lesson> = mutableListOf(),

    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val subjectId: Long = 0,
)