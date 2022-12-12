package nsu.controllers

import nsu.entities.people.Teacher
import nsu.repository.SubjectRepository
import nsu.repository.TeacherRepository
import nsu.service.SubjectService
import nsu.service.TeacherService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class TeacherController(
    private val teacherService: TeacherService,
    private val subjectService: SubjectService,
) {
    // get the list of all teachers
    @GetMapping("teacher")
    fun getTeachers(): ResponseEntity<*> {
        return ResponseEntity.ok(teacherService.findAll())
    }

    // get specific teacher
    @GetMapping("teacher/{teacherId}")
    fun getTeacher(@PathVariable teacherId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(teacherService.findByID(teacherId.toLong()))
    }

    // get the list of subjects by teacherId
    @GetMapping("teacher/{teacherId}/subjects")
    fun getSubjectsByTeacherId(@PathVariable teacherId: Int): ResponseEntity<*> {
        val teacher = teacherService.findByID(teacherId.toLong())
            ?: return ResponseEntity.badRequest().body("No such teacher")
        return ResponseEntity.ok(teacher.subjects)
    }

    // add subject to teacher
    // because the subject is already in the database, we just need to add it to the teacher
    @PostMapping("teacher/{teacherId}/subject/{subjectId}")
    fun addSubjectToTeacher(@PathVariable teacherId: Int, @PathVariable subjectId: Int): ResponseEntity<*> {
        val teacher = teacherService.findByID(teacherId.toLong())
            ?: return ResponseEntity.badRequest().body("No such teacher")
        // maybe this logic will be changed, but for now we can add only one subject to teacher
        val subject = subjectService.findByID(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")
        teacher.subjects.add(subject)
        teacherService.updateTeacher(teacher)
        return ResponseEntity.ok(subject)
    }

    // delete subject from teacher
    // need only to delete the subject from the teacher, not from database itself
    @DeleteMapping("teacher/{teacherId}/subject/{subjectId}")
    fun deleteSubjectFromTeacher(@PathVariable teacherId: Int, @PathVariable subjectId: Int): ResponseEntity<*> {
        val teacher = teacherService.findByID(teacherId.toLong())
            ?: return ResponseEntity.badRequest().body("No such teacher")

        val subject = subjectService.findByID(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")

        teacher.subjects.remove(subject)
        teacherService.updateTeacher(teacher)
        return ResponseEntity.ok(subject)
    }

    // add teacher
    @PostMapping("teacher")
    fun addTeacher(@RequestBody teacher: Teacher): ResponseEntity<*> {
        return try {
            teacherService.addTeacher(teacher)
            ResponseEntity.ok(teacher)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Such teacher already exists")
        }
    }

    // delete teacher
    @DeleteMapping("teacher/{teacherId}")
    fun deleteTeacher(@PathVariable teacherId: Int): ResponseEntity<*> {
        val teacher = teacherService.findByID(teacherId.toLong())
            ?: return ResponseEntity.badRequest().body("No such teacher")

        teacherService.delete(teacherId.toLong())

        return if (teacherService.findByID(teacherId.toLong()) == null) {
            ResponseEntity.ok(teacher)
        } else {
            ResponseEntity.badRequest().body("Something went wrong")
        }
    }

    // update teacher
    @PatchMapping("teacher/{teacherId}")
    fun updateTeacher(@PathVariable teacherId: Int, @RequestBody teacher: Teacher): ResponseEntity<*> {
        val teacherToUpdate = teacherService.findByID(teacherId.toLong())
            ?: return ResponseEntity.badRequest().body("No such teacher")

        teacherToUpdate.name = teacher.name
        teacherToUpdate.subjects = teacher.subjects

        return ResponseEntity.ok(teacherService.updateTeacher(teacherToUpdate))
    }
}