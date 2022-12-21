package nsu.service.impl

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
        val timetableContent = timetableContentService.findAll()
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
                                val thisDayAndTimeInTimeTable = timetableContent.filter {cell ->
                                    cell.day == "$days" && cell.hour == hours
                                }
                                if (thisDayAndTimeInTimeTable.isEmpty()){
                                    val notAvailableRooms = ArrayList<Room>()
                                    timetableContent.forEach{ cell->
                                        notAvailableRooms.add(cell.room!!)
                                    }
                                    val freeRooms = rooms.filter {room->
                                        room !in notAvailableRooms
                                    }

                                    currLessons += 1
                                } else if (currLessons == 4) {
                                    continue
                                }
                            }
                        }
                    }
                }
            }
        }
        return timetableContent
    }

    private fun findFaculties(): List<Faculty> {
        return facultyService.findAll()
    }
}