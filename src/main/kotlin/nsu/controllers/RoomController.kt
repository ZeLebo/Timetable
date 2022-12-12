package nsu.controllers

import nsu.entities.university.Room
import nsu.service.RoomService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class RoomController(
    private val roomService: RoomService,
) {
    @GetMapping("room")
    fun getAll(): ResponseEntity<*> {
        return ResponseEntity.ok(roomService.findAll())
    }
    @PostMapping("room")
    fun create(@RequestBody room: Room): ResponseEntity<*> {
        return ResponseEntity.ok(roomService.addRoom(room))
    }

    @GetMapping("room/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<*> {
        return ResponseEntity.ok(roomService.findByID(id))
    }

    @PatchMapping("room/{id}")
    fun update(@PathVariable id: Long, @RequestBody room: Room): ResponseEntity<*> {
        val roomToUpdate = roomService.findByID(id)
            ?: return ResponseEntity.badRequest().body("No such room")
        roomToUpdate.number = room.number
        roomToUpdate.capacity = room.capacity
        return ResponseEntity.ok(roomService.updateRoom(roomToUpdate))
    }

    @DeleteMapping("room/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<*> {
        roomService.delete(id)
        // check exists
        if (roomService.exists(id)) {
            return ResponseEntity.badRequest().body("Room was not deleted")
        }
        return ResponseEntity.ok("Room was deleted successfully")
    }
}