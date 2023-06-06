package nsu.entities.university

import com.fasterxml.jackson.annotation.JsonIgnore
import nsu.entities.people.Teacher
import jakarta.persistence.*

/**
 * Lesson entity (lesson is like  para)
 * @property name - name of the lesson
 * @property roomType - type of the room, which is needed for this lesson
 * @property subjectType - type of the subject (Lection, seminar, lab)
 * */
@Entity
@Table(name = "lesson")
class Lesson(
    @Column(name = "name")
    var name: String,

    @Column(name = "room_type")
    var roomType: String,


    @Column(name = "subject_type")
    val subjectType: String,

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @field: JsonIgnore
    var subject: Subject? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "teacher_id",
        nullable = true
    )
    @field: JsonIgnore
    var teacher: Teacher? = null,

    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val lessonId: Long = 0,
)