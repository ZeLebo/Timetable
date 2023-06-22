package nsu.algos.repository

import nsu.algos.entities.TimetableOut
import org.springframework.data.jpa.repository.JpaRepository

interface TimetableOutRepository: JpaRepository<TimetableOut, Long> {
    override fun findAll():List<TimetableOut>
}