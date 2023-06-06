package nsu.repository

/**
 * Repository for faculty
 *
 */
import nsu.entities.university.Faculty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FacultyRepository: JpaRepository<Faculty, Long> {
    fun findByFacultyId(facultyId: Long): Faculty?
    fun findByName(name: String): Faculty?
}