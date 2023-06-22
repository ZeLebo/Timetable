package nsu.algos.controller

import nsu.algos.service.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v321")
@CrossOrigin
class GroupAlgoController(
    private val groupAlgoService: GroupAlgoService,
    private val courseService: CourseService,
    private val periodService: PeriodService,
    private val roomAlgoService: RoomAlgoService,
    private val teacherAlgoService: TeacherAlgoService,
    private val algoService: AlgoMakerService,
    private val timetableOutService: TimetableOutService
) {
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

    @GetMapping("generate")
    fun generateTimetable(): ResponseEntity<*> {
        return ResponseEntity.ok(algoService.runAlgo())
    }

    @GetMapping("/group/{group}")
    fun groupTimetable(@PathVariable group: String): ResponseEntity<*> {
        // try to convert the group to number
        return ResponseEntity.ok(timetableOutService.findByGroup(group))
    }

}
