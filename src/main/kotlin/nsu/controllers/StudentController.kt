package nsu.controllers

import nsu.entities.people.Student
import nsu.service.impl.GroupServiceImpl
import nsu.service.impl.StudentServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


class StudentRequest(
    val name: String,
)

@RestController
@RequestMapping("/api/v1/group")
class StudentController(
    private val studentService: StudentServiceImpl,
    private val groupService: GroupServiceImpl,
) {
    @GetMapping("/{groupId}/student")
    fun showStudentForm(@RequestParam id: Int, @PathVariable groupId: String): ResponseEntity<Student> {
        return ResponseEntity.ok(studentService.findByID(id.toLong())!!)
    }

    @PostMapping("/{groupId}/student")
    fun addStudent(@RequestBody request: StudentRequest, @PathVariable groupId: Int): ResponseEntity<*> {
        return try {
            val group = groupService.findByID(groupId.toLong())
                ?: return ResponseEntity.badRequest().body("No such group")
            val student = studentService.addStudent(
                Student(
                    request.name,
                    group
                )
            )
            ResponseEntity.ok(student)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Such student already exists")
        }
    }

    @DeleteMapping("/{groupId}/student")
    fun deleteStudent(@RequestBody request: StudentRequest, @PathVariable groupId: Int): ResponseEntity<*> {
        val student = studentService.findByName(request.name)
            ?: return ResponseEntity.badRequest().body("No such student")

        studentService.delete(student.studentId)

        return if (studentService.findByID(student.studentId) == null) {
            ResponseEntity.ok("Student was deleted successfully")
        } else {
            ResponseEntity.badRequest().body("Something went wrong")
        }
    }

    @PatchMapping("/{groupId}/student")
    fun updateStudent(@RequestBody request: StudentRequest, @PathVariable groupId: Int): ResponseEntity<*> {
        val group = groupService.findByID(groupId.toLong())
            ?: return ResponseEntity.badRequest().body("No such group")

        val student = studentService.findByName(request.name)
            ?: return ResponseEntity.badRequest().body("No such student")

        student.name = request.name
        student.group = group
        return ResponseEntity.ok(studentService.updateStudent(student))
    }
}