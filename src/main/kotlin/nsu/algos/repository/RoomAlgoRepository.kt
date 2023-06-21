package nsu.algos.repository

import nsu.algos.entities.RoomAlgo
import org.springframework.data.jpa.repository.JpaRepository

interface RoomAlgoRepository : JpaRepository<RoomAlgo, Long> {
    //fun findByName(name: Long): RoomAlgo?
    fun findByName(name: String): RoomAlgo?
}