package nsu.controllers

import nsu.entities.people.Teacher
import nsu.repository.SubjectRepository
import nsu.repository.TeacherRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class TeacherController(
    private val teacherService: TeacherRepository,
    private val subjectService: SubjectRepository,
) {
    // get the list of all teachers
    @GetMapping("teacher")
    fun getTeachers(): ResponseEntity<*> {
        return ResponseEntity.ok(teacherService.findAll())
    }

    // get specific teacher
    @GetMapping("teacher/{teacherId}")
    fun getTeacher(@PathVariable teacherId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(teacherService.findById(teacherId.toLong()))
    }

    // get the list of subjects by teacherId
    @GetMapping("teacher/{teacherId}/subjects")
    fun getSubjectsByTeacherId(@PathVariable teacherId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(teacherService.findById(teacherId.toLong()).get().subjects)
    }

    // add subject to teacher
    // because the subject is already in the database, we just need to add it to the teacher
    @PostMapping("teacher/{teacherId}/subject/{subjectId}")
    fun addSubjectToTeacher(@PathVariable teacherId: Int, @PathVariable subjectId: Int): ResponseEntity<*> {
        val teacher = teacherService.findById(teacherId.toLong()).get()
        val subject = subjectService.findBySubjectId(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")
        teacher.subjects.add(subject)
        teacherService.save(teacher)
        return ResponseEntity.ok(subject)
    }

    // delete subject from teacher
    // need only to delete the subject from the teacher, not from database itself
    @DeleteMapping("teacher/{teacherId}/subject/{subjectId}")
    fun deleteSubjectFromTeacher(@PathVariable teacherId: Int, @PathVariable subjectId: Int): ResponseEntity<*> {
        val teacher = teacherService.findById(teacherId.toLong()).get()
        val subject = subjectService.findBySubjectId(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")
        teacher.subjects.remove(subject)
        teacherService.save(teacher)
        return ResponseEntity.ok(subject)
    }

    // add teacher
    @PostMapping("teacher")
    fun addTeacher(@RequestBody teacher: Teacher): ResponseEntity<*> {
        return ResponseEntity.ok(teacherService.save(teacher))
    }

    // delete teacher
    @DeleteMapping("teacher/{teacherId}")
    fun deleteTeacher(@PathVariable teacherId: Int): ResponseEntity<*> {
        teacherService.deleteById(teacherId.toLong())
        return ResponseEntity.ok("Teacher was deleted successfully")
    }

    // update teacher
    @PatchMapping("teacher/{teacherId}")
    fun updateTeacher(@PathVariable teacherId: Int, @RequestBody teacher: Teacher): ResponseEntity<*> {
        val teacherToUpdate = teacherService.findByTeacherId(teacherId.toLong())
            ?: return ResponseEntity.badRequest().body("No such teacher")
        teacherToUpdate.name = teacher.name
        teacherToUpdate.subjects = teacher.subjects
        return ResponseEntity.ok(teacherService.save(teacherToUpdate))
    }
}