package nsu.algos.controller

import nsu.algos.service.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v321")
@CrossOrigin
class GroupAlgoController (
    private val groupAlgoService: GroupAlgoService,
    private val courseService: CourseService,
    private val periodService: PeriodService,
    private val roomAlgoService: RoomAlgoService,
    private val teacherAlgoService: TeacherAlgoService
){
    @GetMapping("group")
    fun getAllGroup(): ResponseEntity<*> {
        return ResponseEntity.ok(groupAlgoService.findAll())
    }

    @GetMapping("course")
    fun getAllCourse(): ResponseEntity<*> {
        return ResponseEntity.ok(courseService.findAll())
    }

    @GetMapping("period")
    fun getAllPeriod(): ResponseEntity<*> {
        return ResponseEntity.ok(periodService.findAll())
    }

    @GetMapping("room")
    fun getAllRooms(): ResponseEntity<*> {
        return ResponseEntity.ok(roomAlgoService.findAll())
    }

    @GetMapping("teacher")
    fun getAllTeachers(): ResponseEntity<*> {
        return ResponseEntity.ok(teacherAlgoService.findAll())
    }


}

fun main() {

}
