package nsu.controllers.timetable

import nsu.entities.university.Faculty
import nsu.entities.university.Specialization
import nsu.entities.university.StudyYear
import nsu.service.FacultyService
import nsu.service.SpecializationService
import nsu.service.StudyYearService
import nsu.service.TimetableMaker
import nsu.service.impl.GroupServiceImpl
import nsu.service.impl.TimetableMakerImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Controller which handles work with timetable
 *
 * @param studyYearService - service logic for work with study years entity
 * @param groupService - service logic for work with groups entity
 * @param specializationService - service logic for work with specializations entity
 * @param timetableMakerService - service logic for creating timetables
 * */
@RestController
@RequestMapping("/api/v1/timetable")
class TimetableController(
    private val studyYearService: StudyYearService,
    private val groupService: GroupServiceImpl,
    private val specializationService: SpecializationService,
    private val facultyService: FacultyService,
    private val timetableMakerService: TimetableMaker
) {

    /**
     * Test method for checking timetable controller
     *
     * @return result of request
     * */
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

    /**
     * Method for GET request for creating timetable entity in the db
     *
     * @return result of request
     * */
    @GetMapping("/create")
    fun createTimetable(): ResponseEntity<*> {
        return ResponseEntity.ok(timetableMakerService.createTimeTable())
    }


    /**
     * Method for GET request for get list of groups from db
     *
     * @return result of request
     * */
    @GetMapping("/group")
    fun groups(): String {
        return "Here's the list of groups: \n1.\t20214\n2.\t20215\n3.\t20216"
    }

    /**
     * Method for GET request for get a timetable for special group
     *
     * @param group - group for which user want to get timetable
     * @return result of request
     * */
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

    /**
     * Method for GET request for get list of teachers from db
     *
     * @return result of request
     * */
    @GetMapping("/teacher")
    fun teachers(): String {
        return "Here's the list of teachers: \n1.\tIvanov\n2.\tPetrov\n3.\tSidorov"
    }

    /**
     * Method for GET request for get a timetable for special teacher
     *
     * @param teacher - teacher for which user want to get timetable
     * @return result of request
     * */
    @GetMapping("/teacher/{teacher}")
    fun teacherTimetable(@PathVariable teacher: String): String {
        return "The server is working, soon at this page you will be able to see the timetable for the teacher $teacher"
    }

    /**
     * Method for GET request for get a list of rooms
     *
     * @return list of groups
     * */
    @GetMapping("/room")
    // return the clickable list of rooms
    fun rooms(): String {
        return "Here's the list of rooms: \n\n1.\t101\n2.\t102\n3.\t103"
    }

    /**
     * Method for get a timetable for specific room
     *
     * @param room - room for which user wants to get the timetable
     * @return timetable for room
     * */
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