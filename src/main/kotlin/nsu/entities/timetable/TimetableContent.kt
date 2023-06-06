package nsu.entities.timetable

import nsu.entities.people.Group
import nsu.entities.people.Teacher
import nsu.entities.university.Lesson
import nsu.entities.university.Room
import jakarta.persistence.*

/**
 * Lesson in timetable entity.
 *
 * @property discipline - name of discipline
 * @property day - weekday of the lesson
 * @property hour - time of the lesson
 * @property teacher - name of the teacher
 * @property group - group which will attend the lesson
 * @property room - room where the lesson will be
 * */
@Entity
@Table(name = "timetable_content")
class TimetableContent(

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "lesson_id",
        nullable = true
    )
    val discipline: Lesson? = null,

    @Column(name = "day")
    val day: String,

    @Column(name = "hour")
    val hour: Int,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "teacher_id",
        nullable = true
    )
    var teacher: Teacher? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "room_id",
        nullable = true
    )
    var room: Room? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "timetable_content_group",
        joinColumns = [JoinColumn(name = "timetable_content_id")],
        inverseJoinColumns = [JoinColumn(name = "group_id")]
    )
    var groups: MutableList<Group> = mutableListOf(),

    @Id
    @Column(name = "timetable_content_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val timetableContentId: Long = 0,
)