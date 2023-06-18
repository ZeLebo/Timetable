package nsu.controllers

import nsu.entities.university.Room
import nsu.service.RoomService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
/**
 * Controller for Rooms
 *
 * @param roomService - service logic for work with room entity
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class RoomController(
    private val roomService: RoomService,
) {
    /**
     * This method get the list of all rooms
     *
     * @return list of all rooms
     */
    @GetMapping("room")
    fun getAll(): ResponseEntity<*> {
        return ResponseEntity.ok(roomService.findAll())
    }

    /**
     * This method add new room
     */
    @PostMapping("room")
    fun create(@RequestBody room: Room): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(roomService.addRoom(room))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
    /**
     * This method get the room by id
     *
     * @return room
     */
    @GetMapping("room/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<*> {
        // check the existence of the room
        val room = roomService.findByID(id)
            ?: return ResponseEntity.badRequest().body("No such room")
        return ResponseEntity.ok(room)
    }

    /**
     * This method update specific room
     */

    @PatchMapping("room/{id}")
    fun update(@PathVariable id: Long, @RequestBody room: Room): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(roomService.updateRoom(id, room))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
    /**
     * This method delete room by id
     *
     */
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