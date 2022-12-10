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

    override fun delete(id: Long) {
        roomRepository.deleteById(id)
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