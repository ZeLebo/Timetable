package nsu.algos.repository

import nsu.algos.entities.RoomAlgo
import org.springframework.data.jpa.repository.JpaRepository

interface RoomAlgoRepository : JpaRepository<RoomAlgo, Long> {
    fun findByRoomId(roomId: Long): RoomAlgo?
    fun findByNumber(number: String): RoomAlgo?
}