package nsu.service.impl

import nsu.entities.people.Group
import nsu.entities.people.Teacher
import nsu.entities.university.*
import nsu.repository.FacultyRepository

// this is service for creating timetable
/**
 *
 * */
class TimetableMaker() {
    private val faculties: ArrayList<Faculty>

    private val csandsdSpecialization = Specialization("")

    init {
        faculties = getFaculties()
    }

    private fun getFaculties(): ArrayList<Faculty>{
        val newFaculties = ArrayList<Faculty>()
        newFaculties.add(Faculty("ФИТ", , ))
    }
}B