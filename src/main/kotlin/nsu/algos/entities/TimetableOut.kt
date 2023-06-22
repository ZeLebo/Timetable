package nsu.algos.entities

import jakarta.persistence.*

@Entity
class TimetableOut(

    @Id
    @Column(name = "TIMETABLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val timetableId: Int = 0,

    @Column(name = "day")
    val day: Int,

    @Column(name = "hour")
    val hour: Int,

    @Column(name = "roomNum")
    val roomNum: String,

    @Column(name = "courseId")
    val courseId: Int,

    @Column(name = "courseName")
    val courseName: String,

    @Column(name = "teacherName")
    val teacherName: String,

    @Column(name = "groupNum")
    val groupNum: String
) {
    constructor() : this(0, 0, 0, "", 0, "", "", "")
}
