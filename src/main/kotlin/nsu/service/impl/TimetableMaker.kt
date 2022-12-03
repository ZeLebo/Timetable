package nsu.service.impl

import nsu.entities.people.Group
import nsu.entities.people.Teacher
import nsu.entities.university.*

// this is service for creating timetable
/**
 *
 * */
class TimetableMaker() {
    private val currentUniversityPlan: UniversityPlan
    private val rooms: ArrayList<Room>
    private val teachers: ArrayList<Teacher>
    private val lessonsForMatan: ArrayList<Lesson>
    private val lessonsForImperat: ArrayList<Lesson>
    private val subjects: ArrayList<Subject>
    private val firstStudyYear: ArrayList<StudyYear>
    private val firstYearGroups: ArrayList<Group>
    private val specializations: ArrayList<Specialization>
    private val faculties: ArrayList<Faculty>

    private val specializationsByFaculties: LinkedHashMap<Faculty, ArrayList<Specialization>>
    private val studyYearByFaculty: LinkedHashMap<Faculty, ArrayList<StudyYear>>
    private val subjectsBySpecialization: LinkedHashMap<Specialization, ArrayList<Subject>>


    init {
        //Zatichki before db creation
        teachers = createTeachers()
        firstYearGroups = createFirstYearGroups()
        lessonsForMatan = createMatanLessons()
        lessonsForImperat = createImperatLessons()
        subjects = createSubjects()
        firstStudyYear = createFirstStudyYear()
        specializations = createSpecializations()
        faculties = createFaculty()
        currentUniversityPlan = createUniversityPlan()
        rooms = createRooms()

        specializationsByFaculties = getSpecializationByFaculty(faculties)
        studyYearByFaculty = getStudyYearByFaculty(faculties)
        subjectsBySpecialization = getSubjectBySpecialization(faculties)


    }

    private fun createRooms(): ArrayList<Room> {
        val rooms = ArrayList<Room>()
        rooms.add(Room(300, "Лекционная"))
        rooms.add(Room(15, "Терминальная"))
        rooms.add(Room(15, "Семинарская"))
        return rooms
    }

    private fun createUniversityPlan(): UniversityPlan {
        return UniversityPlan(faculties)
    }

    private fun createFaculty(): ArrayList<Faculty> {
        val faculty = ArrayList<Faculty>()
        faculty.add(Faculty(specializations, "ФИТ"))
        return faculty
    }

    private fun createSpecializations(): ArrayList<Specialization> {
        val specializationsArray = ArrayList<Specialization>()
        specializationsArray.add(Specialization(firstStudyYear, "Новый поток"))
        return specializationsArray
    }

    private fun createFirstYearGroups(): java.util.ArrayList<Group> {
        val groups = ArrayList<Group>()
        groups.add(Group(1, "22212"))
        groups.add(Group(1, "22213"))
        groups.add(Group(1, "22214"))
        groups.add(Group(1, "22215"))
        groups.add(Group(1, "22216"))
        return groups
    }

    private fun createFirstStudyYear(): ArrayList<StudyYear> {
        val studyYear: ArrayList<StudyYear> = ArrayList()
        studyYear.add(StudyYear(subjects, 1, firstYearGroups))
        return studyYear
    }

    private fun createSubjects(): ArrayList<Subject> {
        val subjects: ArrayList<Subject> = ArrayList()
//        subjects.add(Subject(lessonsForImperat, "Императивное программирование"))
//        subjects.add(Subject(lessonsForMatan, "Матан"))
        return subjects
    }

    private fun createMatanLessons(): ArrayList<Lesson> {
        val lessons: ArrayList<Lesson> = ArrayList()
        lessons.add(Lesson("Матан", "Лекционная", "Лекция"))
        lessons.add(Lesson("Матан", "Семинарская", "Семинар"))
        lessons.add(Lesson("Матан", "Семинарская", "Семинар"))
        lessons.add(Lesson("Матан", "Лекционная", "Лекция"))
        lessons.add(Lesson("Матан", "Семинарская", "Семинар"))
        lessons.add(Lesson("Матан", "Лекционная", "Лекция"))
        return lessons
    }

    private fun createImperatLessons(): ArrayList<Lesson>{
        val lessons: ArrayList<Lesson> = ArrayList()
        lessons.add(Lesson("Императивное программирования", "Лекционная", "Лекция"))
        lessons.add(Lesson("Императивное программирование", "Семинарская", "Семинар"))
        lessons.add(Lesson("Императивное программирования", "Терминальная", "Лабораторная работа"))
        lessons.add(Lesson("Императивное программирование", "Лекционная", "Лекция"))
        lessons.add(Lesson("Императивное программирование", "Семинарская", "Семинар"))
        lessons.add(Lesson("Императивное программирование", "Терминальная", "Лабораторная работа"))
        return lessons
    }

    private fun createTeachers(): ArrayList<Teacher> {
        val generatedTeachers: ArrayList<Teacher> = ArrayList()
        val subject1: LinkedHashMap<Subject, String> = LinkedHashMap()
        subject1[subjects[1]] = "Лекция"
        generatedTeachers.add(Teacher("Aboba Abobusovich", subject1))
        val subject2: LinkedHashMap<Subject, String> = LinkedHashMap()
        subject2[subjects[1]] = "Семинар"
        generatedTeachers.add(Teacher("Mister Misterovich", subject2))
        return generatedTeachers
    }

    private fun getSubjectBySpecialization(faculties: ArrayList<Faculty>): LinkedHashMap<Specialization, ArrayList<Subject>> {
        val subjectsBySpecialization: LinkedHashMap<Specialization, ArrayList<Subject>> = LinkedHashMap()
        faculties.forEach {
            it.specializations.forEach{y ->
                y.studyYears.forEach {x->
                    subjectsBySpecialization[y] = x.subjects
                }
            }
        }
        return subjectsBySpecialization
    }

    private fun getSpecializationByFaculty(faculties: ArrayList<Faculty>): LinkedHashMap<Faculty, ArrayList<Specialization>> {
        val specializationsByFaculties: LinkedHashMap<Faculty, ArrayList<Specialization>> = LinkedHashMap()
        faculties.forEach {
            specializationsByFaculties[it] = it.specializations
        }
        return specializationsByFaculties
    }

    private fun getStudyYearByFaculty(faculties: ArrayList<Faculty>): LinkedHashMap<Faculty, ArrayList<StudyYear>> {
        val studyYearByFaculty: LinkedHashMap<Faculty, ArrayList<StudyYear>> = LinkedHashMap()
        faculties.forEach {
            val faculty: Faculty = it
            it.specializations.forEach { y ->
                studyYearByFaculty[faculty] = y.studyYears
            }
        }
        return studyYearByFaculty
    }
}