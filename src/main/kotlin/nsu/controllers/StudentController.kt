package nsu.controllers

import nsu.entities.people.Student
import nsu.service.impl.StudentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class StudentController(
    private val studentService: StudentService,
) {
    @GetMapping("student")
    fun getAll(): ResponseEntity<*> {
        return ResponseEntity.ok(studentService.findAll())
    }

    @GetMapping("/student/{studentId}")
    fun showStudentForm(@PathVariable studentId: Int): ResponseEntity<Student> {
        return ResponseEntity.ok(studentService.findByID(studentId.toLong())!!)
    }

    @DeleteMapping("student/{studentId}")
    fun deleteStudent(@PathVariable studentId: Int): ResponseEntity<*> {
        return try {
            studentService.delete(studentId.toLong())
            ResponseEntity.ok("Student deleted")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PatchMapping("student/{studentId}")
    fun updateStudent(@PathVariable studentId: Int, @RequestBody request: Student): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(studentService.updateStudent(studentId.toLong(), request))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}