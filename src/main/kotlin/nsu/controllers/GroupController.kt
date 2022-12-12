package nsu.controllers

import nsu.entities.people.Group
import nsu.service.StudyYearService
import nsu.service.impl.GroupServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/")
class GroupController(
    private val studyYearService: StudyYearService,
    private val groupService: GroupServiceImpl,
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

    // get the list of all groups for specific study year
    @GetMapping("studyYear/{studyYearId}/group")
    fun getGroupsForStudyYear(@PathVariable studyYearId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(studyYearService.findByID(studyYearId.toLong()))
    }

    // add new group to specific study year
    @PostMapping("studyYear/{studyYearId}/group")
    fun addGroup(@RequestBody request: Group, @PathVariable studyYearId: Int): ResponseEntity<*> {
        return try {
            val st = studyYearService.findByID(studyYearId.toLong())
                ?: return ResponseEntity.badRequest().body("No such study year")
            val group = groupService.addGroup(request)

            st.groups.add(group)
            ResponseEntity.ok(studyYearService.updateYear(st))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    //    @PostMapping("group")
//    fun addGroup(@RequestBody request: Group): ResponseEntity<*> {
//        return try {
//            val group = groupService.addGroup(request)
//            ResponseEntity.ok(group)
//        } catch (e: Exception) {
//            ResponseEntity.badRequest().body(e.message)
//        }
//    }
//
    // delete specific group
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

    // update specific group
    @PatchMapping("group/{id}")
    fun updateGroup(@PathVariable id: Int, @RequestBody request: Group): ResponseEntity<*> {
        val group = groupService.findByID(id.toLong())
            ?: return ResponseEntity.badRequest().body("No such group")

        group.number = request.number
        group.students = request.students
        groupService.updateGroup(group)

        return if (groupService.findByID(group.groupId) == request) {
            ResponseEntity.ok("Group was updated successfully")
        } else {
            ResponseEntity.badRequest().body("Something went wrong")
        }
    }
}