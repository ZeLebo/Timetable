package nsu.service

import nsu.entities.university.Faculty

interface FacultyService {
    fun addFaculty(faculty: Faculty): Faculty
    fun updateFaculty(faculty: Faculty): Faculty
    fun findByName(faculty: String):Faculty?
    fun delete(id: Long)
    fun exists(id: Long): Boolean
    fun exists(number: String): Boolean

    fun findAll(): List<Faculty>
}