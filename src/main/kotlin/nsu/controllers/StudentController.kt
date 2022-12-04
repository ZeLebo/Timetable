package nsu.controllers

import nsu.entities.people.Student
import nsu.service.impl.GroupServiceImpl
import nsu.service.impl.StudentServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/api/v1")
class StudentController(
    private val studentService: StudentServiceImpl,
    private val groupService: GroupServiceImpl,
) {
    class StudentRequest(
        val name: String,
    )

    @GetMapping("student")
    fun getAll(): ResponseEntity<*> {
        return ResponseEntity.ok(studentService.findAll())
    }

    @GetMapping("/student/{studentId}")
    fun showStudentForm(@PathVariable studentId: Int): ResponseEntity<Student> {
        return ResponseEntity.ok(studentService.findByID(studentId.toLong())!!)
    }

    // get the list of all students for specific group
    @GetMapping("group/{groupId}/student")
    fun getStudentsForGroup(@PathVariable groupId: Int): ResponseEntity<*> {
        // check if the group exists
        val group = groupService.findByID(groupId.toLong())
            ?: return ResponseEntity.badRequest().body("No such group")
        return ResponseEntity.ok(group.students)
    }

    @PostMapping("group/{groupId}/student")
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

    @DeleteMapping("student/{studentId}")
    fun deleteStudent(@PathVariable studentId: Int): ResponseEntity<*> {
        val student = studentService.findByID(studentId.toLong())
            ?: return ResponseEntity.badRequest().body("No such student")
        studentService.delete(student.studentId)
        return if (studentService.findByID(studentId.toLong()) == null) {
            ResponseEntity.ok("Student deleted")
        } else {
            ResponseEntity.badRequest().body("Something went wrong")
        }
    }

    @PatchMapping("group/{groupId}/student")
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