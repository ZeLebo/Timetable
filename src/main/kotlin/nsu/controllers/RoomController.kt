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
        return try {
            ResponseEntity.ok(roomService.addRoom(room))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @GetMapping("room/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<*> {
        // check the existence of the room
        val room = roomService.findByID(id)
            ?: return ResponseEntity.badRequest().body("No such room")
        return ResponseEntity.ok(room)
    }

    @PatchMapping("room/{id}")
    fun update(@PathVariable id: Long, @RequestBody room: Room): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(roomService.updateRoom(id, room))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @DeleteMapping("room/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<*> {
        return try {
            roomService.delete(id)
            ResponseEntity.ok("Room deleted")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}