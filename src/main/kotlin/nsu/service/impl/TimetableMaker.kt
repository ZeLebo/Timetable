package nsu.service.impl

import nsu.entities.university.*
import nsu.service.*

// this is service for creating timetable
/**
 *
 * */
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
    private val specializations: List<Specialization>
    private val studyYears: List<StudyYear>
    private val subjects: List<Subject>

    init {
        faculties = findFaculties()
        specializations = findSpecializations()
        studyYears = findStudyYears()
        subjects = findSubjects()
    }

    private fun findSubjects(): List<Subject> {
        return subjectService.findAll()
    }


    private fun findStudyYears(): List<StudyYear> {
        return studyYearService.findAll()
    }

    private fun findSpecializations(): List<Specialization> {
        return specializationService.findAll()
    }

    private fun findFaculties(): List<Faculty> {
        return facultyService.findAll()
    }
}