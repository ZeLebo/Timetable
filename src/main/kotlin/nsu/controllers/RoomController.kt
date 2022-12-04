package nsu.controllers

import nsu.entities.university.Room
import nsu.repository.RoomRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class RoomController(
    // todo change to service with logic
    private val roomService: RoomRepository,
) {
    @GetMapping("room")
    fun getAll(): ResponseEntity<*> {
        return ResponseEntity.ok(roomService.findAll())
    }
    @PostMapping("room")
    fun create(@RequestBody room: Room): ResponseEntity<*> {
        return ResponseEntity.ok(roomService.save(room))
    }

    @GetMapping("room/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<*> {
        return ResponseEntity.ok(roomService.findById(id))
    }

    @PatchMapping("room/{id}")
    fun update(@PathVariable id: Long, @RequestBody room: Room): ResponseEntity<*> {
        val roomToUpdate = roomService.findByRoomId(id)
            ?: return ResponseEntity.badRequest().body("No such room")
        roomToUpdate.number = room.number
        roomToUpdate.capacity = room.capacity
        return ResponseEntity.ok(roomService.save(roomToUpdate))
    }

    @DeleteMapping("room/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<*> {
        roomService.deleteById(id)
        // check exists
        if (roomService.existsById(id)) {
            return ResponseEntity.badRequest().body("Room was not deleted")
        }
        return ResponseEntity.ok("Room was deleted successfully")
    }
}