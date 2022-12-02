package nsu.repository

import nsu.entities.people.Group
import org.springframework.data.jpa.repository.JpaRepository

interface GroupRepository : JpaRepository<Group, Long> {
    fun findByNumber(number: String): Group?
}