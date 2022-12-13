package nsu.controllers

import nsu.entities.university.Lesson
import nsu.entities.university.Subject
import nsu.service.SubjectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class SubjectController(
    private val subjectService: SubjectService,
) {
    // get the list of all subjects
    @GetMapping("subject")
    fun getSubjects(): ResponseEntity<*> {
        return ResponseEntity.ok(subjectService.findAll())
    }

    // get specific subject
    @GetMapping("subject/{subjectId}")
    fun getSubject(@PathVariable subjectId: Int): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(subjectService.findByID(subjectId.toLong()))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // delete specific subject for specific study year
    @DeleteMapping("subject/{subjectId}")
    fun deleteSubject(@PathVariable subjectId: Int): ResponseEntity<*> {
        return try {
            subjectService.delete(subjectId.toLong())
            ResponseEntity.ok("Subject deleted")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // update specific subject for specific study year
    @PatchMapping("subject/{subjectId}")
    fun updateSubject(@PathVariable subjectId: Int, @RequestBody subject: Subject): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(subjectService.updateSubject(subjectId.toLong(), subject))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // get the list of all lessons by subjectId
    @GetMapping("subject/{subjectId}/lesson")
    fun getLessonsBySubjectId(@PathVariable subjectId: Int): ResponseEntity<*> {
        val subject = subjectService.findByID(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")
        return ResponseEntity.ok(subject.lessons)
    }

    // add new lesson to specific subject
    @PostMapping("subject/{subjectId}/lesson")
    fun addLesson(@PathVariable subjectId: Int, @RequestBody request: Lesson): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(subjectService.addLesson(subjectId.toLong(), request))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}