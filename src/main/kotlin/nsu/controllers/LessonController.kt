package nsu.controllers

import nsu.entities.university.Lesson
import nsu.service.LessonService
import nsu.service.SubjectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class LessonController(
    // todo change to service with logic
    private val lessonService: LessonService,
    private val subjectService: SubjectService,
) {
    // get the list of all lessons
    @GetMapping("lesson")
    fun getLessons(): ResponseEntity<*> {
        return ResponseEntity.ok(lessonService.findAll())
    }

    // get specific lesson
    @GetMapping("lesson/{lessonId}")
    fun getLesson(@PathVariable lessonId: Int): ResponseEntity<*> {
        // check the existence of the lesson
        val lesson = lessonService.findByID(lessonId.toLong())
            ?: return ResponseEntity.badRequest().body("No such lesson")
        return ResponseEntity.ok(lesson)
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
        val subject = subjectService.findByID(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")

        // add the lesson to the database
        val lesson = lessonService.addLesson(request)
        subject.lessons.add(lesson)

        subjectService.updateSubject(subject)

        return ResponseEntity.ok(request)
    }

    // delete specific lesson
    @DeleteMapping("lesson/{lessonId}")
    fun deleteLesson(@PathVariable lessonId: Int): ResponseEntity<*> {
        val lesson = lessonService.findByID(lessonId.toLong())
            ?: return ResponseEntity.badRequest().body("No such lesson")

        lessonService.delete(lesson.lessonId)

        return if (lessonService.findByID(lessonId.toLong()) == null) {
            ResponseEntity.ok("Lesson was deleted successfully")
        } else {
            ResponseEntity.badRequest().body("Lesson was not deleted")
        }
    }

    // update specific lesson
    @PatchMapping("lesson/{lessonId}")
    fun updateLesson(@PathVariable lessonId: Int, @RequestBody lesson: Lesson): ResponseEntity<*> {
        val lessonToUpdate = lessonService.findByID(lessonId.toLong())
            ?: return ResponseEntity.badRequest().body("No such lesson")

        lessonToUpdate.name = lesson.name
        lessonToUpdate.roomType = lesson.roomType

        lessonService.updateLesson(lessonToUpdate)

        return ResponseEntity.ok(lessonToUpdate)
    }
}