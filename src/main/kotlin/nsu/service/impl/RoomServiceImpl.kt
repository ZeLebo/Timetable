package nsu.service.impl

import nsu.entities.university.Room
import nsu.repository.RoomRepository
import nsu.service.RoomService
import org.springframework.stereotype.Service
import java.lang.RuntimeException


@Service
class RoomServiceImpl(
    private val roomRepository: RoomRepository
): RoomService {
    override fun addRoom(room: Room): Room {
        if (roomRepository.findByNumber(room.number) != null) {
            throw RuntimeException("Room already exists")
        }
        return roomRepository.save(room)
    }

    override fun updateRoom(room: Room): Room {
        return roomRepository.save(room)
    }

    override fun updateRoom(id: Long, room: Room): Room {
        val roomDb = this.findByID(id)
            ?: throw RuntimeException("Room not found")
        roomDb.number = room.number
        roomDb.capacity = room.capacity
        roomDb.roomType = room.roomType
        return this.updateRoom(roomDb)
    }

    override fun delete(id: Long) {
        // check if room exists
        this.findByID(id) ?: throw RuntimeException("Room not found")
        roomRepository.deleteById(id)

        if (this.findByID(id) != null) {
            throw RuntimeException("Room not deleted")
        }
    }

    override fun findByID(id: Long): Room? {
        return roomRepository.findById(id).orElse(null)
    }

    override fun findByNumber(number: String): Room? {
        return roomRepository.findByNumber(number)
    }
    override fun exists(id: Long): Boolean {
        return roomRepository.existsById(id)
    }

    override fun exists(number: String): Boolean {
        return roomRepository.findByNumber(number) != null
    }

    override fun findAll(): List<Room> {
        return roomRepository.findAll()
    }
}