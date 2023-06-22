package nsu.algos.service.impl

import nsu.algos.entities.*
import nsu.algos.service.*
import org.springframework.stereotype.Service

@Service
class AlgoMakerImpl(
    private val groupAlgoService: GroupAlgoService,
    private val courseService: CourseService,
    private val periodService: PeriodService,
    private val roomAlgoService: RoomAlgoService,
    private val teacherAlgoService: TeacherAlgoService
) : AlgoMaker {

//    val
//    val a:Map<Int,Map<>>

    override fun runAlgo(): List<Period> {
        TODO("Not yet implemented")
    }

}

fun main() {
    //мапа по дням недели
    //<dayOfWeek,<roomId, List<freeTime>>>
    val daysMap: MutableMap<Int, MutableMap<Int, MutableList<Boolean>>> = mutableMapOf(
        1 to mutableMapOf(
            1 to mutableListOf(true, false, false),
            2 to mutableListOf(true, false, false),
            3 to mutableListOf(true, false, false)
        ),
        2 to mutableMapOf(
            1 to mutableListOf(false, false, false),
            2 to mutableListOf(false, false, false),
            3 to mutableListOf(false, false, false)
        ),
        3 to mutableMapOf(
            1 to mutableListOf(false, false, false),
            2 to mutableListOf(false, false, false),
            3 to mutableListOf(false, false, false)
        )
    )
//    testMap[1]!![1]!![1] =
//    daysMap[1]!![1]!![0] = false
//    println(daysMap[1]!![1]!![0])

    // мапа по группе и свободному времени
    // day groupId freeTime


    val groupMap: MutableMap<Int, MutableMap<Int, MutableList<Boolean>>> = mutableMapOf(
        1 to mutableMapOf(
            1 to mutableListOf(true, true, true),
            2 to mutableListOf(true, true, true),
            3 to mutableListOf(true, true, true)
        ),
        2 to mutableMapOf(
            1 to mutableListOf(false, true, true),
            2 to mutableListOf(true, true, true),
            3 to mutableListOf(true, true, true)
        ),
        3 to mutableMapOf(
            1 to mutableListOf(true, true, true),
            2 to mutableListOf(true, true, true),
            3 to mutableListOf(true, true, true)
        )
    )


    val teacherCourseMap: MutableMap<Int, Int> = mutableMapOf(
        2 to 2,
        1 to 3,
        3 to 1
    )

    // day, courseId, freeTime
    val courseMap: MutableMap<Int, MutableMap<Int, MutableList<Boolean>>> = mutableMapOf(
        1 to mutableMapOf(
            1 to mutableListOf(true, true, true),
            2 to mutableListOf(true, true, true),
            3 to mutableListOf(true, true, true)
        ),
        2 to mutableMapOf(
            1 to mutableListOf(true, true, false),
            2 to mutableListOf(false, true, false),
            3 to mutableListOf(true, false, true)
        ),
        3 to mutableMapOf(
            1 to mutableListOf(false, true, true),
            2 to mutableListOf(false, true, true),
            3 to mutableListOf(true, true, false)
        )
    )


    val intersectionMap: MutableMap<Int, Int> = mutableMapOf()

    for ((day, dayData) in daysMap) {
        for ((roomId, freeTimeList) in dayData) {
            for ((courseId, courseData) in courseMap) {
                for (i in 0 until 3) {
                    if (courseData[roomId]!![i] == freeTimeList[i] && courseData[roomId]!![i]) {
                        intersectionMap[courseId] = intersectionMap.getOrDefault(courseId, 0) + 1
                    }
                }
            }
        }
    }

//    for((day, courseData) in courseMap){
//        val freeTimeSet: MutableSet<Pair<Int,Int>> = HashSet()
//        courseData.forEach { (t, u) ->  freeTimeSet.add(Pair(day, ))}
//
//    }
    //course day room
    //println(courseMap)
    //courseid, windows


//    val availableTimeForCourseSorted = availableTimeForCourse
//
//    availableTimeForCourse.keys.sortedBy { availableTimeForCourse[it]!!.size }

    // courseid - свободные окна
//    val mapOfIntersection: MutableMap<Int, Int> = mutableMapOf()
//    for (i in availableTimeForCourse.keys.sortedBy { availableTimeForCourse[it]!!.size }) {
//        var counter = 0
//        for (j in availableTimeForRoom.values) {
//            counter += (availableTimeForCourse[i]!! intersect j).size
//        }
//        mapOfIntersection[i] = counter
//        //println("$counter $i - counter")
//    }

    //roomId


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
    teachers.add(TeacherAlgo(4, "Zlyga"))
    teachers.add(TeacherAlgo(5, "Postovalov"))

    val rooms: MutableList<RoomAlgo> = mutableListOf()
    rooms.add(RoomAlgo(12, "2128", 50))
    rooms.add(RoomAlgo(21, "3343", 30))
    rooms.add(RoomAlgo(31, "2128", 200))
    rooms.add(RoomAlgo(41, "1234", 24))

    val courses: MutableList<Course> = mutableListOf()
    courses.add(Course(1, "OOP", "Lecture", teachers[0], 1, 1, 2))
    courses.add(Course(2, "Math", "Lecture", teachers[1], 2, 1, 3))
    courses.add(Course(3, "Law", "Seminar", teachers[2], 3, 2, 2))
    courses.add(Course(4, "DAO", "Lecture", teachers[3], 4, 2, 3))
    courses.add(Course(5, "Aboba", "Lecture", teachers[4], 5, 2, 5))

    val availableTimeForCourse: MutableMap<Int, MutableSet<Pair<Int, Int>>> = mutableMapOf(
        1 to mutableSetOf(Pair(1, 1), Pair(1, 2)),
        2 to mutableSetOf(Pair(3, 1), Pair(3, 3)),
        3 to mutableSetOf(Pair(2, 4), Pair(2, 3)),
        4 to mutableSetOf(Pair(4, 4), Pair(4, 3)),
        5 to mutableSetOf(Pair(5, 4)),
    )

    //generate mutable set of Pairs
    val pairs: MutableSet<Pair<Int, Int>> = mutableSetOf()
//    for (i in 1 until 7) {
//        for(j in 1 until 6 ){
//            pairs.add(Pair (i, j))
//        }
//    }

    println(pairs)
    // from courses to availableTimeForCourse
    courses.forEach {
        availableTimeForCourse[it.id] = mutableSetOf(Pair(it.day, it.hour))
    }

    println("$availableTimeForCourse - time")


    //roomId, availableTime
    val availableTimeForRoom: MutableMap<Int, MutableSet<Pair<Int, Int>>> = mutableMapOf(
        1 to mutableSetOf(Pair(1, 2), Pair(1, 2)),
        2 to mutableSetOf(Pair(3, 1), Pair(3, 3)),
        3 to mutableSetOf(Pair(2, 4), Pair(2, 3)),
        4 to mutableSetOf(Pair(4, 4), Pair(4, 3)),
        5 to mutableSetOf(Pair(5, 4)),
    )

    var roomSet = mutableSetOf(12, 21, 31, 41, 51)
    // day room courseId?
    var timetable: MutableMap<Int, MutableMap<Int, MutableList<Int?>>> = mutableMapOf()

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

    val timetableOut: MutableList<TimetableOut> = mutableListOf()
    for ((day, timetableData) in timetable) {
        for ((roomId, lesson) in timetableData) {
            for(( lessonNum, courseId ) in lesson.withIndex()){
                if (courseId != null){
                    val roomNum = rooms.filter { it.id.toInt() == roomId }.first().name
                    val courseName = courses.filter { it.id == courseId }.first().name
                    val teacherId = courses.filter { it.id == courseId }.first().teacherId.teacherId
                    val teacherName = teachers.filter { it.teacherId == teacherId }.first().name
                    val groupId = courses.filter { it.id == courseId }.first().groupId
                    val groupName = groups.filter { it.id.toInt() == groupId }.first().name
                    timetableOut.add(TimetableOut(day, lessonNum, roomId, roomNum, courseId, courseName, teacherName, groupName))
                }
            }
        }
    }

    println("$timetableOut - timetableOut")
    timetable.forEach { println(it) }


    //println(timetable)

    println("$groupCourseMap - group")

//
//    for (i in mapOfIntersection.keys.sortedBy { mapOfIntersection[it]!! }){
//        for(j in )
//    }
//
//    println(mapOfIntersection)
//    println(mapOfIntersection.keys.sortedBy { mapOfIntersection[it]!! })
//

    println(availableTimeForCourse)


    println(availableTimeForRoom)

// Вывод карты с количеством пересечений для каждого курса
//    for ((courseId, intersectionCount) in intersectionMap) {
//        println("Курс $courseId: $intersectionCount пересечений")
//    }


//    val intersectionMap: MutableMap<Int, Int> = mutableMapOf()
//
//    for ((courseId, courseData) in courseMap) {
//        for ((day, dayData) in daysMap) {
//            for ((roomId, freeTimeList) in dayData) {
//                val courseFreeTimeList = courseData[day]
//                if (courseFreeTimeList != null) {
//                    val intersectionCount = freeTimeList.zip(courseFreeTimeList)
//                        .count { (d1, d2) -> d1 && d2 }
//                    intersectionMap[courseId] = intersectionMap.getOrDefault(courseId, 0) + intersectionCount
//                }
//            }
//        }
//    }
//
//// Вывод карты с количеством пересечений для каждого курса
//    for ((courseId, intersectionCount) in intersectionMap) {
//        println("Курс $courseId: $intersectionCount пересечений")
//    }


}