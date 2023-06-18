package nsu.controllers

import nsu.entities.people.Teacher
import nsu.service.TeacherService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
/**
 * Controller for Teachers
 *
 * @param teacherService - service logic for work with teacher entity
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class TeacherController(
    private val teacherService: TeacherService,
) {
    /**
     * This method  get the list of all teachers
     *
     * @return list of all teachers
     */

    @GetMapping("teacher")
    fun getTeachers(): ResponseEntity<*> {
        return ResponseEntity.ok(teacherService.findAll())
    }
    /**
     * This method  get specific teacher
     *
     */

    @GetMapping("teacher/{teacherId}")
    fun getTeacher(@PathVariable teacherId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(teacherService.findByID(teacherId.toLong()))
    }
    /**
     * This method get the list of subjects by teacherId
     *
     */

    @GetMapping("teacher/{teacherId}/lessons")
    fun getLessonsByTeacherId(@PathVariable teacherId: Int): ResponseEntity<*> {
        val teacher = teacherService.findByID(teacherId.toLong())
            ?: return ResponseEntity.badRequest().body("No such teacher")
        return ResponseEntity.ok(teacher.lessons)
    }

    /**
     * This method  add subject to teacher
     * because the subject is already in the database, we just need to add it to the teacher
     */


    @PostMapping("teacher/{teacherId}/lessons/{lessonId}")
    fun addLessonToTeacher(@PathVariable teacherId: Int, @PathVariable lessonId: Int): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(teacherService.addLesson(teacherId.toLong(), lessonId.toLong()))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
    /**
     * This method  delete subject from teacher
     * need only to delete the subject from the teacher, not from database itself
     */

    @DeleteMapping("teacher/{teacherId}/lesson/{lessonId}")
    fun removeLessonFromTeacher(@PathVariable teacherId: Int, @PathVariable lessonId: Int): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(teacherService.removeLessonFromTeacher(teacherId.toLong(), lessonId.toLong()))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
    /**
     * This method add teacher
     */
    @PostMapping("teacher")
    fun addTeacher(@RequestBody teacher: Teacher): ResponseEntity<*> {
        return try {
            teacherService.addTeacher(teacher)
            ResponseEntity.ok(teacher)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Such teacher already exists")
        }
    }
    /**
     * This method delete teacher
     */
    @DeleteMapping("teacher/{teacherId}")
    fun deleteTeacher(@PathVariable teacherId: Int): ResponseEntity<*> {
        return try {
            teacherService.delete(teacherId.toLong())
            ResponseEntity.ok("Teacher was deleted successfully")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    /**
     * This method update teacher
     */
    @PatchMapping("teacher/{teacherId}")
    fun updateTeacher(@PathVariable teacherId: Int, @RequestBody teacher: Teacher): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(teacherService.updateTeacher(teacherId.toLong(), teacher))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}