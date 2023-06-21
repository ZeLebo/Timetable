package nsu.algos.entities

import jakarta.persistence.*

@Entity
@Table(name = "PERIOD")
class Period(
    @Id
    @Column(name = "PERIOD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val periodId: Long,

    @Column(name = "DAY")
    val day: Int,

    @Column(name = "LINE")
    val line: Int,

    @Column(name = "TYPE")
    val type: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "ROOM_ID",
        nullable = true
    )
    val roomId: RoomAlgo,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "TEACHER_ID",
        nullable = true
    )
    val teacherId: TeacherAlgo,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "COURSE_ID",
        nullable = true
    )
    val courseId: Course,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "GROUP_ID",
        nullable = true
    )
    val groupId: GroupAlgo,

    ) {
    constructor() : this(
        0, 0, 0, "", RoomAlgo(), TeacherAlgo(), Course(), GroupAlgo()
    )
}