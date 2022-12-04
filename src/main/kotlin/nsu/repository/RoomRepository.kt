package nsu.repository

import nsu.entities.university.Room
import org.springframework.data.jpa.repository.JpaRepository

interface RoomRepository : JpaRepository<Room, Long> {
    fun findByRoomId(roomId: Long): Room?
    fun findByNumber(number: String): Room?
}