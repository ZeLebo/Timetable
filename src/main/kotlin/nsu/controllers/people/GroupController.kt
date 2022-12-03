package nsu.controllers.people

import nsu.entities.people.Group
import nsu.entities.people.Student
import nsu.service.impl.GroupServiceImpl
import nsu.service.impl.StudentServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class StudentRequest(
    val first: String,
    val last: String,
    val groupId: Int?
)

@RestController
@RequestMapping("/api/v1/group")
class GroupController(
    private val studentService: StudentServiceImpl,
    private val groupService: GroupServiceImpl,
) {
    @GetMapping("/{groupId}/student")
    fun showStudentForm(@RequestParam id: Int): ResponseEntity<Student> {
        return ResponseEntity.ok(studentService.findByID(id.toLong())!!)
    }

    @PostMapping("/{groupId}/student")
    fun addStudent(@RequestBody request: StudentRequest): ResponseEntity<*> {
        return try {
            val group = groupService.findByID(request.groupId!!.toLong())
            val student = studentService.addStudent(
                Student(
                    request.first,
                    request.last,
                    group
                )
            )
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
                Group(
                    request.number,
                    request.students.map {
                        studentService.addStudent(
                            Student(
                                it.first,
                                it.last,
                                it.group,
                            )
                        )
                    }.toMutableList()
                )
            )
            ResponseEntity.ok(group)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}