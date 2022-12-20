package nsu.service.impl

import nsu.entities.people.Group
import nsu.entities.people.Teacher
import nsu.entities.timetable.TimetableContent
import nsu.entities.university.*
import nsu.service.*

// this is service for creating timetable
class TimetableMaker(
    private val facultyService: FacultyService,
    private val timetableContentService: TimetableContentService
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
        faculties.forEach {
            val specializations = it.specializations
            specializations.forEach{specialization ->
                val studyYears = specialization.studyYears
                studyYears.forEach{studyYear ->
                    val groups = studyYear.groups
                    groups.forEach{group ->
                        for(days in 1..6){
                            var currLessons = 0
                            for (hours in 1..7){
                                val dayInTimetable = timetableContentService.findSpecialDay("$days")
                                val hoursInTimetable = timetableContentService.findSpecialHour(hours)
                                val emptyList = arrayListOf<TimetableContent>()
                                if (currLessons<=4 && dayInTimetable.size == 0 && hoursInTimetable.size == 0){

                                    currLessons+=1
                                }
                            }
                        }
                    }
                }
            }
        }
        val specializations = ArrayList<Specialization>()

        return timetableContent
    }

    private fun findFaculties(): List<Faculty> {
        return facultyService.findAll()
    }
}