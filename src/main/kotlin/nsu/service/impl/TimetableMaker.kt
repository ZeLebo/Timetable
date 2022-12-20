package nsu.service.impl

import nsu.entities.people.Group
import nsu.entities.people.Teacher
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
    private val teachers: List<Teacher>
    private val rooms: List<Room>
    private val groups: List<Group>
    private val lessons: List<Lesson>

    init {
        faculties = findFaculties()
        specializations = findSpecializations()
        studyYears = findStudyYears()
        subjects = findSubjects()
        teachers = findTeachers()
        rooms = findRooms()
        groups = findGroups()
        lessons = findLessons()
    }

    private fun findLessons(): List<Lesson> {
        return lessonService.findAll()
    }

    private fun findGroups(): List<Group> {
        return groupService.findAll()
    }

    private fun findRooms(): List<Room> {
        return roomService.findAll()
    }

    private fun findTeachers(): List<Teacher> {
        return teacherService.findAll()
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