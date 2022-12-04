package nsu.controllers

import nsu.entities.people.Group
import nsu.entities.people.Student
import nsu.service.impl.GroupServiceImpl
import nsu.service.impl.StudentServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/api/v1/")
class GroupController(
    private val studentService: StudentServiceImpl,
    private val groupService: GroupServiceImpl,
) {

    @GetMapping("group")
    fun showGroupForm(@RequestParam id: Int): ResponseEntity<*> {
        val group = groupService.findByID(id.toLong())
            ?: return ResponseEntity.badRequest().body("No such group")
        return ResponseEntity.ok(group)
    }

    @PostMapping("group")
    fun addGroup(@RequestBody request: Group): ResponseEntity<*> {
        return try {
            val group = groupService.addGroup(request)
            // create a group and get it id
//            val groupId = groupService.addGroup(Group(request.number, mutableListOf())).groupId
//            val group = groupService.addGroup(
//                Group(
//                    request.number,
//                    request.students.map {
//                        studentService.addStudent(
//                            Student(
//                                it.name,
//                                it.group,
//                            )
//                        )
//                    }.toMutableList()
//                )
//            )
            ResponseEntity.ok(group)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @DeleteMapping("group/{id}")
    fun deleteGroup(@PathVariable id: Int): ResponseEntity<*> {
        val group = groupService.findByID(id.toLong())
            ?: return ResponseEntity.badRequest().body("No such group")
        groupService.delete(group.groupId)
        return if (groupService.findByID(group.groupId) == null) {
            ResponseEntity.ok("Group was deleted successfully")
        } else {
            ResponseEntity.badRequest().body("Something went wrong")
        }
    }
}