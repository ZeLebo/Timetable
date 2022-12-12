package nsu.service

import nsu.entities.university.Faculty

interface FacultyService {
    fun addFaculty(faculty: Faculty): Faculty
    fun updateFaculty(faculty: Faculty): Faculty
    fun findByName(name: String):Faculty?
    fun findByID(id: Long): Faculty?
    fun delete(id: Long)
    fun exists(id: Long): Boolean
    fun exists(name: String): Boolean

    fun findAll(): List<Faculty>
}