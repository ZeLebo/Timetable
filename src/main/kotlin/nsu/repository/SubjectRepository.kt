package nsu.repository
/**
 * Repository for subject
 *
 */
import nsu.entities.university.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository: JpaRepository<Subject, Long> {
    fun findBySubjectId(subjectId: Long): Subject?
    fun findByName(name: String): Subject?
}