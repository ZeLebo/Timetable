package nsu.service.impl

import nsu.entities.people.Group
import nsu.entities.people.Teacher
import nsu.entities.timetable.TimetableContent
import nsu.entities.university.*
import nsu.service.*

// this is service for creating timetable
class TimetableMaker(
    private val facultyService: FacultyService,
    private val groupService: GroupService,
    private val lessonService: LessonService,
    private val roomService: RoomService,
    private val specializationService: SpecializationService,
    private val studyYearService: StudyYearService,
    private val subjectService: SubjectService,
    private val teacherService: TeacherService,
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
        val timetableContent = ArrayList<TimetableContent>()
        faculties.forEach {
            val specializations = it.specializations
            specializations.forEach{specialization ->
                val studyYears = specialization.studyYears
                studyYears.forEach{studyYear ->
                    val groups = studyYear.groups
                    groups.forEach{
                        group ->

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