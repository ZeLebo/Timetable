package nsu.controllers.timetable

import nsu.entities.people.Group
import nsu.entities.university.Faculty
import nsu.entities.university.Specialization
import nsu.entities.university.StudyYear
import nsu.service.FacultyService
import nsu.service.SpecializationService
import nsu.service.StudyYearService
import nsu.service.impl.GroupServiceImpl
import nsu.service.impl.TimetableMaker
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

// here are the request mapping
@RestController
@RequestMapping("/api/v1/timetable")
class TimetableController(
    private val studyYearService: StudyYearService,
    private val groupService: GroupServiceImpl,
    private val specializationService: SpecializationService,
    private val facultyService: FacultyService,
    private val timetableMaker: TimetableMaker
) {
    @GetMapping("/test")
    fun test(): ResponseEntity<*> {
        val faculty = Faculty(
            "Факультет информационных технологий 5",
            mutableListOf(
                Specialization(
                    "Информационные системы и технологии 5",
                    null,
                    mutableListOf(
                        StudyYear(
                            2,
                            ),
                        StudyYear(
                            3,
                        ),
                    ),
                ),
            )
        )
        return ResponseEntity.ok(facultyService.addFaculty(faculty))
    }

    @GetMapping("/create")
    fun createTimetable(): ResponseEntity<*>{
        return ResponseEntity.ok(timetableMaker.createTimeTable())
    }

    @GetMapping("/group")
    fun groups(): String {
        return "Here's the list of groups: \n1.\t20214\n2.\t20215\n3.\t20216"
    }

    @GetMapping("/group/{group}")
    fun groupTimetable(@PathVariable group: String): String {
        // try to convert the group to number
        return try {
            val groupInt = group.toInt()
            "The server is working, soon at this page you will be able to see the timetable for the group $group"
        } catch (e: NumberFormatException) {
            "The group must be a number"
        }
    }

    @GetMapping("/teacher")
    fun teachers(): String {
        return "Here's the list of teachers: \n1.\tIvanov\n2.\tPetrov\n3.\tSidorov"
    }

    @GetMapping("/teacher/{teacher}")
    fun teacherTimetable(@PathVariable teacher: String): String {
        return "The server is working, soon at this page you will be able to see the timetable for the teacher $teacher"
    }

    @GetMapping("/room")
    // return the clickable list of rooms
    fun rooms(): String {
        return "Here's the list of rooms: \n\n1.\t101\n2.\t102\n3.\t103"
    }

    @GetMapping("/room/{room}")
    fun roomTimetable(@PathVariable room: String): String {
        // try to convert the room to int
        return try {
            val roomInt = room.toInt()
            "The server is working, soon at this page you will be able to see the timetable for the room $roomInt"
        } catch (e: NumberFormatException) {
            "The room number must be an integer"
        }
    }
}