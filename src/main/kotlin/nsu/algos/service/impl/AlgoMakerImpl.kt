package nsu.algos.service.impl

import nsu.algos.entities.*
import nsu.algos.service.*
import org.springframework.stereotype.Service

@Service
class AlgoMakerImpl(
    private val groupAlgoService: GroupAlgoService,
    private val courseService: CourseService,
    private val roomAlgoService: RoomAlgoService,
    private val teacherAlgoService: TeacherAlgoService,
) : AlgoMakerService {

    val groups: List<GroupAlgo> = groupAlgoService.findAll()
    val teachers: List<TeacherAlgo> = teacherAlgoService.findAll()
    val rooms: List<RoomAlgo> = roomAlgoService.findAll()
    val courses: List<Course> = courseService.findAll()

    override fun runAlgo(): List<TimetableOut> {

        val availableTimeForCourse: MutableMap<Int, MutableSet<Pair<Int, Int>>> = mutableMapOf()
        courses.forEach {
            availableTimeForCourse[it.id] = mutableSetOf(Pair(it.day, it.hour))
        }

        val roomSet: MutableSet<Int> = mutableSetOf()
        for (room in rooms) {
            roomSet.add(room.id.toInt())
        }
        // day room courseId?
        val timetable: MutableMap<Int, MutableMap<Int, MutableList<Int?>>> = mutableMapOf()

        // groupId
        val groupCourseMap: MutableMap<Int, Set<Pair<Int, Int>>> = mutableMapOf()

        for (i in 0 until 6) {
            timetable[i] = mutableMapOf()
            roomSet.forEach { timetable[i]!![it] = arrayOfNulls<Int?>(5).toMutableList() }
        }
        for (j in availableTimeForCourse.keys.sortedBy { availableTimeForCourse[it]!!.size }) {

            val dayList: MutableList<Int> = mutableListOf()
            availableTimeForCourse[j]!!.forEach { dayList.add(it.first - 1) }
            val hourList: MutableList<Int> = mutableListOf()
            availableTimeForCourse[j]!!.forEach { hourList.add(it.second - 1) }

            dayList.forEach { dayFromList ->
                timetable[dayFromList]!!.forEach { tt ->
                    var t: Int? = null
                    var lim = 0
                    hourList.forEach { hourFromList ->
                        if (tt.value[hourFromList] == null && lim != 1) {
                            timetable[dayFromList]!![tt.key]!![hourFromList] = j
                            t = hourFromList

                            val groupTemp = courses.filter { it.id == j }.first().groupId
                            if (!groupCourseMap.keys.contains(groupTemp)) {
                                groupCourseMap[groupTemp] = mutableSetOf(Pair(dayFromList + 1, hourFromList + 1))
                                lim = 1
                            }
                        }
                    }
                    (t?.let { hourList.remove(it) })
                }
            }

        }

        var count = 0
        val timetableOut: MutableList<TimetableOut> = mutableListOf()
        for ((day, timetableData) in timetable) {
            for ((roomId, lesson) in timetableData) {
                for ((lessonNum, courseId) in lesson.withIndex()) {
                    if (courseId != null) {
                        val roomNum = rooms.filter { it.id.toInt() == roomId }.first().name
                        val courseName = courses.filter { it.id == courseId }.first().name
                        val teacherId = courses.filter { it.id == courseId }.first().teacherId.teacherId
                        val teacherName = teachers.filter { it.teacherId == teacherId }.first().name
                        val groupId = courses.filter { it.id == courseId }.first().groupId
                        val groupName = groups.filter { it.id.toInt() == groupId }.first().name
                        timetableOut.add(
                            TimetableOut(
                                count,
                                day,
                                lessonNum,
                                roomNum,
                                courseId,
                                courseName,
                                teacherName,
                                groupName
                            )
                        )
                        count += 1
                    }
                }
            }
        }
        return timetableOut
    }

}

fun main() {

    val groups: MutableList<GroupAlgo> = mutableListOf()
    groups.add(GroupAlgo(1, "20214", 13))
    groups.add(GroupAlgo(2, "20215", 12))
    groups.add(GroupAlgo(3, "20213", 11))
    groups.add(GroupAlgo(4, "19213", 11))
    groups.add(GroupAlgo(5, "19214", 12))

    val teachers: MutableList<TeacherAlgo> = mutableListOf()
    teachers.add(TeacherAlgo(1, "Vlasov"))
    teachers.add(TeacherAlgo(2, "Vaskevich"))
    teachers.add(TeacherAlgo(3, "Dmitrievski"))
    teachers.add(TeacherAlgo(4, "Zlygostev"))
    teachers.add(TeacherAlgo(5, "Postovalov"))

    val rooms: MutableList<RoomAlgo> = mutableListOf()
    rooms.add(RoomAlgo(12, "2128", 50))
    rooms.add(RoomAlgo(21, "3343", 30))
    rooms.add(RoomAlgo(31, "3107", 200))
    rooms.add(RoomAlgo(41, "1234", 24))

    val courses: MutableList<Course> = mutableListOf()
    courses.add(Course(1, "OOP", "Lecture", teachers[0], 1, 1, 2))
    courses.add(Course(2, "Math", "Lecture", teachers[1], 2, 1, 2))
    courses.add(Course(3, "Law", "Seminar", teachers[2], 3, 2, 2))
    courses.add(Course(4, "DAO", "Lecture", teachers[3], 4, 2, 3))
    courses.add(Course(5, "Test1", "Lecture", teachers[4], 5, 2, 3))
    courses.add(Course(6, "Test2", "Lecture", teachers[0], 5, 3, 5))
    courses.add(Course(7, "Test3", "Lecture", teachers[1], 5, 3, 5))
    courses.add(Course(8, "Test4", "Lecture", teachers[2], 5, 3, 4))
    courses.add(Course(9, "Test5", "Lecture", teachers[3], 5, 4, 5))
    courses.add(Course(10, "Test6", "Lecture", teachers[2], 5, 4, 1))
    courses.add(Course(11, "Test7", "Lecture", teachers[1], 5, 4, 1))
    courses.add(Course(12, "Test8", "Lecture", teachers[3], 5, 3, 5))
    courses.add(Course(13, "Test9", "Lecture", teachers[2], 5, 1, 5))
    courses.add(Course(14, "Test10", "Lecture", teachers[1], 5, 5, 5))
    courses.add(Course(15, "Test10", "Lecture", teachers[1], 5, 5, 5))

    val availableTimeForCourse: MutableMap<Int, MutableSet<Pair<Int, Int>>> = mutableMapOf()

    courses.forEach {
        availableTimeForCourse[it.id] = mutableSetOf(Pair(it.day, it.hour))
    }

    val roomSet: MutableSet<Int> = mutableSetOf()
    for (room in rooms) {
        roomSet.add(room.id.toInt())
    }
    // day room courseId?
    val timetable: MutableMap<Int, MutableMap<Int, MutableList<Int?>>> = mutableMapOf()

    // groupId
    val groupCourseMap: MutableMap<Int, Set<Pair<Int, Int>>> = mutableMapOf()

    for (i in 0 until 6) {
        timetable[i] = mutableMapOf()
        roomSet.forEach { timetable[i]!![it] = arrayOfNulls<Int?>(5).toMutableList() }
    }
    for (j in availableTimeForCourse.keys.sortedBy { availableTimeForCourse[it]!!.size }) {

        val dayList: MutableList<Int> = mutableListOf()
        availableTimeForCourse[j]!!.forEach { dayList.add(it.first - 1) }
        val hourList: MutableList<Int> = mutableListOf()
        availableTimeForCourse[j]!!.forEach { hourList.add(it.second - 1) }

        dayList.forEach { dayFromList ->
            timetable[dayFromList]!!.forEach { tt ->
                var t: Int? = null
                var lim = 0
                hourList.forEach { hourFromList ->
                    if (tt.value[hourFromList] == null && lim != 1) {
                        timetable[dayFromList]!![tt.key]!![hourFromList] = j
                        t = hourFromList

                        val groupTemp = courses.filter { it.id == j }.first().groupId
                        if (!groupCourseMap.keys.contains(groupTemp)) {
                            groupCourseMap[groupTemp] = mutableSetOf(Pair(dayFromList + 1, hourFromList + 1))
                            lim = 1
                        }
                    }
                }
                (t?.let { hourList.remove(it) })
            }
        }

    }
    var count = 0
    val timetableOut: MutableList<TimetableOut> = mutableListOf()
    for ((day, timetableData) in timetable) {
        for ((roomId, lesson) in timetableData) {
            for ((lessonNum, courseId) in lesson.withIndex()) {
                if (courseId != null) {
                    val roomNum = rooms.filter { it.id.toInt() == roomId }.first().name
                    val courseName = courses.filter { it.id == courseId }.first().name
                    val teacherId = courses.filter { it.id == courseId }.first().teacherId.teacherId
                    val teacherName = teachers.filter { it.teacherId == teacherId }.first().name
                    val groupId = courses.filter { it.id == courseId }.first().groupId
                    val groupName = groups.filter { it.id.toInt() == groupId }.first().name
                    timetableOut.add(
                        TimetableOut(
                            count,
                            day,
                            lessonNum,
                            roomNum,
                            courseId,
                            courseName,
                            teacherName,
                            groupName
                        )
                    )
                    count += 1
                }
            }
        }
    }

    timetableOut.forEach { println(it.roomNum) }
}