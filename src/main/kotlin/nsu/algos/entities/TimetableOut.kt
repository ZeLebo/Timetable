package nsu.algos.entities

data class TimetableOut(
    val day: Int, val roomId: Int, val roomNum: Int,
    val courseId: Int, val courseName: String, val teacherName: String, val groupNum: String
)
