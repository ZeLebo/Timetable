package nsu.repository

import nsu.entities.university.Faculty
import org.springframework.data.jpa.repository.JpaRepository

interface FacultyRepository: JpaRepository<Faculty, Long> {
    fun findByFacultyId(facultyId: Long): Faculty?
    fun findByName(name: String): Faculty?
}