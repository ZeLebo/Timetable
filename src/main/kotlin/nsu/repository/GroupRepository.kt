package nsu.repository
/**
 * Repository for group
 *
 */
import nsu.entities.people.Group
import org.springframework.data.jpa.repository.JpaRepository

interface GroupRepository : JpaRepository<Group, Long> {
    fun findByNumber(number: String): Group?
    fun existsByNumber(number: String): Boolean
}