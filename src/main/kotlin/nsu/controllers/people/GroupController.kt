package nsu.controllers.people

import nsu.controllers.request.GroupRequest
import nsu.controllers.request.StudentRequest
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
    fun showStudentForm(): String {
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @PostMapping("student")
    fun addStudent(@RequestBody Student: StudentRequest): ResponseEntity<*> {
        return try {
            val student = studentService.addStudent(
                Student(Student.first_name,
                    Student.last_name)
            )
            // add student to group
            val group = groupService.findByNumber(Student.group_number)
                ?: return ResponseEntity.badRequest().body("No such group")

            group.students.add(student)
            groupService.updateGroup(group)

            ResponseEntity.ok().body(student)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Such student already exists")
        }
    }

    @GetMapping("group")
    fun showGroupForm(): String {
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @PostMapping("group")
    fun addGroup(@RequestBody request: GroupRequest): ResponseEntity<*> {
        return try {
            val group = groupService.addGroup(
                Group(request.number,
                    request.students.map {
                        studentService.addStudent(
                            Student(
                                it.first_name,
                                it.last_name
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