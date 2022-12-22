package nsu.service.impl

import nsu.entities.people.Group
import nsu.entities.timetable.TimetableContent
import nsu.entities.university.*
import nsu.service.*

// this is service for creating timetable
class TimetableMaker(
    private val facultyService: FacultyService,
    private val timetableContentService: TimetableContentService,
    private val roomService: RoomService
) {
    private val faculties: List<Faculty>
    private val timetableContent: List<TimetableContent>
    private val maxLessonsOneDay = 4

    init {
        faculties = findFaculties()

        timetableContent = createTimeTable()
    }

    private fun createTimeTable(): List<TimetableContent> {
        val result = ArrayList<TimetableContent>()
        var timetableContent = timetableContentService.findAll()
        val rooms = roomService.findAll()
        faculties.forEach {
            val specializations = it.specializations
            specializations.forEach { specialization ->
                val studyYears = specialization.studyYears
                studyYears.forEach { studyYear ->
                    val groups = studyYear.groups
                    groups.forEach { group ->
                        for (days in 1..6) {
                            var currLessons = 0
                            for (hours in 1..7) {
                                if (currLessons == maxLessonsOneDay) {
                                    break
                                }
                                val thisDayAndTimeInTimeTable = timetableContent.filter { cell ->
                                    cell.day == "$days" && cell.hour == hours && cell.groups == group
                                }
                                if (thisDayAndTimeInTimeTable.isEmpty()) {
                                    val notAvailableRooms = ArrayList<Room>()
                                    timetableContent.forEach { cell ->
                                        notAvailableRooms.add(cell.room!!)
                                    }
                                    val freeRooms = rooms.filter { room ->
                                        room !in notAvailableRooms
                                    }
                                    if (freeRooms.isEmpty()) {
                                        continue
                                    }
                                    var lessons = ArrayList<Lesson>()
                                    studyYear.subjects.forEach { subject ->
                                        lessons = subject.lessons as ArrayList<Lesson>
                                    }
                                    val lectures = ArrayList<Lesson>()
                                    val seminars = ArrayList<Lesson>()
                                    val laboratory = ArrayList<Lesson>()
                                    lessons.forEach { lesson ->
                                        when (lesson.subjectType) {
                                            "Лекция" -> {
                                                lectures.add(lesson)
                                            }
                                            "Семинар" -> {
                                                seminars.add(lesson)
                                            }
                                            "Лабораторное занятие" -> {
                                                laboratory.add(lesson)
                                            }
                                        }
                                    }
                                    val lectureCell = timetableContent.filter { cell ->
                                        cell.discipline == lectures[0]
                                    }
                                    if (lectureCell.isEmpty()) {
                                        val lessonTeacher = lectures[0].teacher
                                        val roomType = lectures[0].roomType
                                        val isTeacherAvailable = timetableContent.filter { cell ->
                                            cell.day == "$days" && cell.hour == hours && lessonTeacher != cell.teacher
                                        }
                                        if (isTeacherAvailable.isEmpty()) {
                                            continue
                                        }
                                        var studentsOnLesson = 0
                                        val groupsOnLesson = ArrayList<Group>()
                                        lectures[0].subject!!.StudyYear!!.groups.forEach { gr ->
                                            groupsOnLesson.add(gr)
                                        }
                                        val availableGroupsOnLesson = ArrayList<Group>()
                                        groupsOnLesson.forEach {groupOnLesson ->
                                            timetableContent.forEach{ cell ->
                                                if (cell.day == "$days" && cell.hour == hours && groupOnLesson !in cell.groups){
                                                    availableGroupsOnLesson.add(groupOnLesson)
                                                }
                                            }
                                        }
                                        if(availableGroupsOnLesson.size < groupsOnLesson.size){
                                            continue
                                        }
                                        lectures[0].subject!!.StudyYear!!.groups.forEach { gr ->
                                            studentsOnLesson += gr.students.size
                                            groupsOnLesson.add(gr)
                                        }
                                        val suitableRooms = rooms.filter { room ->
                                            room.capacity >= studentsOnLesson
                                        }
                                        val filteredSuitableRooms = ArrayList<Room>()
                                        timetableContent.forEach { cell->
                                            suitableRooms.forEach{suitableRoom ->
                                                if (cell.day == "$days" && cell.hour == hours && suitableRoom != cell.room){
                                                    filteredSuitableRooms.add(suitableRoom)
                                                }
                                            }
                                        }
                                        result.add(TimetableContent(lectures[0], "$days", hours, lessonTeacher, suitableRooms[0], availableGroupsOnLesson))
                                        timetableContent = result
                                    }
                                    currLessons += 1
                                }
                            }
                        }
                    }
                }
            }
        }
        return result

    }

    private fun findFaculties(): List<Faculty> {
        return facultyService.findAll()
    }
}