package nsu.services.impl

import nsu.entities.university.*

// this is service for creating timetable

class TimetableMaker(universityPlan: UniversityPlan, private val rooms: ArrayList<Room>) {
    private val faculties: ArrayList<Faculty> = universityPlan.faculties
    private val specializationsByFaculties: LinkedHashMap<Faculty, ArrayList<Specialization>> = LinkedHashMap()
    private val studyYearByFaculty: LinkedHashMap<Faculty, ArrayList<StudyYear>> = LinkedHashMap()

    init {
        getSpecializationByFaculty()
        getStudyYearByFaculty()
    }

    private fun getSpecializationByFaculty() {
        faculties.forEach {
            specializationsByFaculties[it] = it.specializations
        }
    }

    private fun getStudyYearByFaculty(){
        faculties.forEach {
            val faculty: Faculty = it
            it.specializations.forEach { y ->
                studyYearByFaculty[faculty] = y.studyYears
            }
        }
    }

}

fun main() {
}