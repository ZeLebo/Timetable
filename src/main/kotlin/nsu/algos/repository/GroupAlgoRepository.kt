package nsu.algos.repository
/**
 * Repository for group
 *
 */
import nsu.algos.entities.GroupAlgo
import org.springframework.data.jpa.repository.JpaRepository

interface GroupAlgoRepository : JpaRepository<GroupAlgo, Long> {
    fun findByNumber(number: String): GroupAlgo?
    fun existsByNumber(number: String): Boolean
}