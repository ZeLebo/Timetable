package nsu.controllers

import nsu.entities.people.Group
import nsu.entities.people.Student
import nsu.service.GroupService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Controller for group
 *
 * @param groupService - service logic for work with group entity
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
class GroupController(
    private val groupService: GroupService,
) {
    /**
     * This method get the list of all groups
     *
     * @return list of all groups
     */

    @GetMapping("group")
    fun getGroups(): ResponseEntity<*> {
        return ResponseEntity.ok(groupService.findAll())
    }

    /**
     * This method get specific group
     *
     * @return group by id
     */
    @GetMapping("group/{groupId}")
    fun getGroup(@PathVariable groupId: Int): ResponseEntity<*> {
        val group = groupService.findByID(groupId.toLong())
            ?: return ResponseEntity.badRequest().body("No such group")
        return ResponseEntity.ok(group)
    }
    /**
     * This method delete group by id
     *
     */

    @DeleteMapping("group/{id}")
    fun deleteGroup(@PathVariable id: Int): ResponseEntity<*> {
        return try {
            groupService.delete(id.toLong())
            ResponseEntity.ok("Group deleted")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
    /**
     * This method update group's id and name
     */
    @PatchMapping("group/{id}")
    fun updateGroup(@PathVariable id: Int, @RequestBody request: Group): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(groupService.updateGroup(id.toLong(), request))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    /**
     *  This method get the list of all students for specific group
     *
     *  @return list of students for specific group
     */

    @GetMapping("group/{groupId}/student")
    fun getStudentsForGroup(@PathVariable groupId: Int): ResponseEntity<*> {
        val group = groupService.findByID(groupId.toLong())
            ?: return ResponseEntity.badRequest().body("No such group")
        return ResponseEntity.ok(group.students)
    }

    /**
     * This method add student into group
     */
    @PostMapping("group/{groupId}/student")
    fun addStudent(@RequestBody student: Student, @PathVariable groupId: Int): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(groupService.addStudent(groupId.toLong(), student))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Such student already exists")
        }
    }
}