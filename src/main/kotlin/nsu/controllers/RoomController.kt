package nsu.controllers

import nsu.entities.university.Room
import nsu.repository.RoomRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/room")
class RoomController(
    // todo change to service with logic
    private val roomService: RoomRepository,
) {
    @GetMapping
    fun getAll(): ResponseEntity<*> {
        return ResponseEntity.ok(roomService.findAll())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<*> {
        return ResponseEntity.ok(roomService.findById(id))
    }

    @PostMapping
    fun create(@RequestBody room: Room): ResponseEntity<*> {
        return ResponseEntity.ok(roomService.save(room))
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody room: Room): ResponseEntity<*> {
        val roomToUpdate = roomService.findByRoomId(id)
            ?: return ResponseEntity.badRequest().body("No such room")
        roomToUpdate.number = room.number
        roomToUpdate.capacity = room.capacity
        return ResponseEntity.ok(roomService.save(roomToUpdate))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<*> {
        roomService.deleteById(id)
        // check exists
        if (roomService.existsById(id)) {
            return ResponseEntity.badRequest().body("Room was not deleted")
        }
        return ResponseEntity.ok("Room was deleted successfully")
    }
}