package nsu.controllers

import nsu.entities.university.Lesson
import nsu.service.LessonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class LessonController(
    private val lessonService: LessonService,
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

    // delete specific lesson
    @DeleteMapping("lesson/{lessonId}")
    fun deleteLesson(@PathVariable lessonId: Int): ResponseEntity<*> {
        return try {
            lessonService.delete(lessonId.toLong())
            ResponseEntity.ok("Lesson deleted")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // update specific lesson
    @PatchMapping("lesson/{lessonId}")
    fun updateLesson(@PathVariable lessonId: Int, @RequestBody lesson: Lesson): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(lessonService.updateLesson(lessonId.toLong(), lesson))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}