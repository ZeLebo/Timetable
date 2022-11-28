package nsu.services.impl

import nsu.entities.people.Group
import nsu.entities.people.Student
import nsu.entities.people.Teacher
import nsu.entities.university.*

// this is service for creating timetable

class TimetableMaker(universityPlan: UniversityPlan, private val rooms: ArrayList<Room>, private val teachers: ArrayList<Teacher>) {
    private val faculties: ArrayList<Faculty> = universityPlan.faculties
    private val specializationsByFaculties: LinkedHashMap<Faculty, ArrayList<Specialization>>
    private val studyYearByFaculty: LinkedHashMap<Faculty, ArrayList<StudyYear>>
    private val subjectsBySpecialization: LinkedHashMap<Specialization, ArrayList<Subject>>


    init {
        specializationsByFaculties = getSpecializationByFaculty(faculties)
        studyYearByFaculty = getStudyYearByFaculty(faculties)
        subjectsBySpecialization = getSubjectBySpecialization(faculties)
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

    fun getEverything(){
        println(specializationsByFaculties[faculties[0]])
    }

}

fun main() {
    val lessonsForMatanForNewThread: ArrayList<Lesson> = ArrayList()
    lessonsForMatanForNewThread.add(Lesson("Матан", "Лекционная", "Лекция"))
    lessonsForMatanForNewThread.add(Lesson("Матан","Семинарская", "Семинар"))

    val lessonsForImperativka: ArrayList<Lesson> = ArrayList()
    lessonsForImperativka.add(Lesson("Imperativka", "Lekcionnaya", "Lekciya"))
    lessonsForImperativka.add(Lesson("Imperativka", "Seminarskaya", "Seminar"))

    val subjectsForNewThread: ArrayList<Subject> = ArrayList()
    subjectsForNewThread.add(Subject(lessonsForMatanForNewThread, "Матан"))
    subjectsForNewThread.add(Subject(lessonsForImperativka, "Imperativka"))

    val studyYearsForFit: ArrayList<StudyYear> = ArrayList()
    val students: ArrayList<Student> = ArrayList()
    students.add(Student("Malov Alexey"))
    val groups: ArrayList<Group> = ArrayList()
    groups.add(Group(students, 1, "2020"))
    studyYearsForFit.add(StudyYear(subjectsForNewThread, 1, groups))
    val fitSpecializations: ArrayList<Specialization> = ArrayList()
    fitSpecializations.add(Specialization(studyYearsForFit, "New Thread()"))
    val faculties: ArrayList<Faculty> = ArrayList()
    faculties.add(Faculty(fitSpecializations, "FIT"))
    val universityPlan = UniversityPlan(faculties)

    val rooms: ArrayList<Room> = ArrayList()
    rooms.add(Room(30, "Лекционная"))
    rooms.add(Room(10, "Семинарская"))

    val teachers: ArrayList<Teacher> = ArrayList()
    val linkedHashMap: LinkedHashMap<Subject, String> = LinkedHashMap()
    linkedHashMap[Subject(lessonsForMatanForNewThread, "Матан")] = "Семинары"
    teachers.add(Teacher("Abobus Abobusovich",linkedHashMap))

    val timetableMaker = TimetableMaker(universityPlan, rooms, teachers)
    timetableMaker.getEverything()
}