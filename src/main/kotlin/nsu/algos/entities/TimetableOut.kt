package nsu.algos.entities

data class TimetableOut(
    val day: Int, val hour: Int, val roomId: Int, val roomNum: String,
    val courseId: Int, val courseName: String, val teacherName: String, val groupNum: String
)
