package nsu.algos.repository
/**
 * Repository for group
 *
 */
import nsu.algos.entities.GroupAlgo
import org.springframework.data.jpa.repository.JpaRepository

interface GroupAlgoRepository : JpaRepository<GroupAlgo, Long> {
    fun findByName(name: String): GroupAlgo?
    fun existsByName(name: String): Boolean
}