package nsu.algos.service.impl

import nsu.algos.entities.RoomAlgo
import nsu.algos.repository.RoomAlgoRepository
import nsu.algos.service.RoomAlgoService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class RoomAlgoServiceImpl(
    private val roomRepository: RoomAlgoRepository
): RoomAlgoService {
    override fun addRoom(room: RoomAlgo): RoomAlgo {
        if (roomRepository.findByName(room.name) != null) {
            throw RuntimeException("Room already exists")
        }
        return roomRepository.save(room)
    }

    override fun updateRoom(room: RoomAlgo): RoomAlgo {
        return roomRepository.save(room)
    }

    override fun updateRoom(id: Long, room: RoomAlgo): RoomAlgo {
        val roomDb = this.findByID(id)
            ?: throw RuntimeException("Room not found")
        roomDb.name = room.name
        roomDb.capacity = room.capacity
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

    override fun findByID(id: Long): RoomAlgo? {
        return roomRepository.findById(id).orElse(null)
    }

    override fun findByNumber(number: String): RoomAlgo? {
        return roomRepository.findByName(number)
    }
    override fun exists(id: Long): Boolean {
        return roomRepository.existsById(id)
    }

    override fun exists(number: String): Boolean {
        return roomRepository.findByName(number) != null
    }

    override fun findAll(): List<RoomAlgo> {
        return roomRepository.findAll()
    }
}