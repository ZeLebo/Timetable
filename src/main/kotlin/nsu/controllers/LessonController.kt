package nsu.controllers

import nsu.entities.university.Lesson
import nsu.repository.LessonRepository
import nsu.repository.SubjectRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class LessonController(
    // todo change to service with logic
    private val lessonService: LessonRepository,
    private val subjectService: SubjectRepository,
) {
    // get the list of all lessons
    @GetMapping("lesson")
    fun getLessons(): ResponseEntity<*> {
        return ResponseEntity.ok(lessonService.findAll())
    }

    // get specific lesson
    @GetMapping("lesson/{lessonId}")
    fun getLesson(@PathVariable lessonId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(lessonService.findById(lessonId.toLong()))
    }

    // get the list of all lessons by subjectId
    @GetMapping("subject/{subjectId}/lesson")
    fun getLessonsBySubjectId(@PathVariable subjectId: Int): ResponseEntity<*> {
        val subject = subjectService.findBySubjectId(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")
        return ResponseEntity.ok(subject.lessons)
    }

    // add new lesson to specific subject
    @PostMapping("subject/{subjectId}/lesson")
    fun addLesson(@PathVariable subjectId: Int, @RequestBody lesson: Lesson): ResponseEntity<*> {
        val subject = subjectService.findBySubjectId(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")
        subject.lessons.add(lesson)
        subjectService.save(subject)
        return ResponseEntity.ok(lesson)
    }

    // delete specific lesson
    @DeleteMapping("lesson/{lessonId}")
    fun deleteLesson(@PathVariable lessonId: Int): ResponseEntity<*> {
        val lesson = lessonService.findByLessonId(lessonId.toLong())
            ?: return ResponseEntity.badRequest().body("No such lesson")

        lessonService.delete(lesson)

        return if (lessonService.findByLessonId(lessonId.toLong()) == null) {
            ResponseEntity.ok("Lesson was deleted successfully")
        } else {
            ResponseEntity.badRequest().body("Lesson was not deleted")
        }
    }

    // update specific lesson
    @PatchMapping("lesson/{lessonId}")
    fun updateLesson(@PathVariable lessonId: Int, @RequestBody lesson: Lesson): ResponseEntity<*> {
        val lessonToUpdate = lessonService.findByLessonId(lessonId.toLong())
            ?: return ResponseEntity.badRequest().body("No such lesson")

        lessonToUpdate.name = lesson.name
        lessonToUpdate.roomType = lesson.roomType

        lessonService.save(lessonToUpdate)

        return ResponseEntity.ok(lessonToUpdate)
    }
}