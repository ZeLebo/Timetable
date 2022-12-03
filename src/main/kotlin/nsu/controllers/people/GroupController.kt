package nsu.controllers.people

import nsu.entities.people.Group
import nsu.entities.people.Student
import nsu.service.impl.GroupServiceImpl
import nsu.service.impl.StudentServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class GroupController(
    private val studentService: StudentServiceImpl,
    private val groupService: GroupServiceImpl,
) {
    @GetMapping("student")
    fun showStudentForm(@RequestParam id: Int): ResponseEntity<Student> {
        return ResponseEntity.ok(studentService.findByID(id.toLong())!!)
    }

    @PostMapping("student")
    fun addStudent(@RequestBody request: Student): ResponseEntity<*> {
        return try {
            val student = studentService.addStudent(
                Student(request.first,
                    request.last,
                    request.groupNumber)
            )
            // add student to group
            val group = groupService.findByNumber(request.groupNumber)
                ?: return ResponseEntity.badRequest().body("No such group")

            group.students.add(student)
            groupService.updateGroup(group)

            ResponseEntity.ok(student)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Such student already exists")
        }
    }

    @GetMapping("group")
    fun showGroupForm(@RequestParam id: Int): ResponseEntity<*> {
        val group = groupService.findByID(id.toLong())
            ?: return ResponseEntity.badRequest().body("No such group")
        return ResponseEntity.ok(group)
    }

    @PostMapping("group")
    fun addGroup(@RequestBody request: Group): ResponseEntity<*> {
        return try {
            val group = groupService.addGroup(
                Group(request.number,
                    request.students.map {
                        studentService.addStudent(
                            Student(
                                it.first,
                                it.last,
                                it.groupNumber,
                            )
                        )
                    }.toMutableList()
                )
            )
            ResponseEntity.ok().body(group)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}