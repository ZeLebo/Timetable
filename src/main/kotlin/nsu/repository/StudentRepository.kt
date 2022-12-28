package nsu.repository
/**
 * Repository for students
 *
 */
import nsu.entities.people.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Long> {
    fun findByStudentId(studentId: Long): Student?
    fun findByName(name: String): Student?
}