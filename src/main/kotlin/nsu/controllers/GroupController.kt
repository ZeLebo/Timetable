package nsu.controllers

import nsu.entities.people.Group
import nsu.entities.people.Student
import nsu.service.GroupService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 *
 */
@RestController
@RequestMapping("/api/v1/")
class GroupController(
    private val groupService: GroupService,
) {
    // get the list of all groups
    @GetMapping("group")
    fun getGroups(): ResponseEntity<*> {
        return ResponseEntity.ok(groupService.findAll())
    }

    // get specific group
    @GetMapping("group/{groupId}")
    fun getGroup(@PathVariable groupId: Int): ResponseEntity<*> {
        val group = groupService.findByID(groupId.toLong())
            ?: return ResponseEntity.badRequest().body("No such group")
        return ResponseEntity.ok(group)
    }

    // delete specific group
    @DeleteMapping("group/{id}")
    fun deleteGroup(@PathVariable id: Int): ResponseEntity<*> {
        return try {
            groupService.delete(id.toLong())
            ResponseEntity.ok("Group deleted")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // update specific group
    @PatchMapping("group/{id}")
    fun updateGroup(@PathVariable id: Int, @RequestBody request: Group): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(groupService.updateGroup(id.toLong(), request))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // get the list of all students for specific group
    @GetMapping("group/{groupId}/student")
    fun getStudentsForGroup(@PathVariable groupId: Int): ResponseEntity<*> {
        val group = groupService.findByID(groupId.toLong())
            ?: return ResponseEntity.badRequest().body("No such group")
        return ResponseEntity.ok(group.students)
    }

    @PostMapping("group/{groupId}/student")
    fun addStudent(@RequestBody student: Student, @PathVariable groupId: Int): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(groupService.addStudent(groupId.toLong(), student))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Such student already exists")
        }
    }
}