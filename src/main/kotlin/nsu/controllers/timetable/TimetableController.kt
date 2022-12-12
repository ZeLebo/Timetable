package nsu.controllers.timetable

import nsu.entities.people.Group
import nsu.entities.people.Student
import nsu.entities.university.Specialization
import nsu.entities.university.StudyYear
import nsu.repository.GroupRepository
import nsu.repository.StudyYearRepository
import nsu.service.SpecializationService
import nsu.service.impl.GroupServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

// here are the request mapping
@RestController
@RequestMapping("/api/v1/timetable")
class TimetableController(
    private val studyYearRepository: StudyYearRepository,
    private val groupService: GroupServiceImpl,
    private val specializationService: SpecializationService
) {
    @GetMapping("/test")
    fun test(): ResponseEntity<*> {

//        var group = groupService.addGroup(
//            Group(
//                "adddd",
//                students = mutableListOf(Student("valera")),
//                groupId = 2
//            )
//        )

        val specialization = Specialization(
            "fit",
            studyYears = mutableListOf(
                StudyYear(
                    1,
                    groups = mutableListOf(groupService.findByNumber("adddd")!!)
                )
            )
        )

        val studyYear = StudyYear(
            1,
            null,
            mutableListOf(groupService.findByNumber("adddd")!!),
            mutableListOf(),
            0
        )
//        var st = groupService.addGroup(group)
        var sty = specializationService.updateSpecialization(specialization)
//        sty.studyYears[0].groups[0].students[0].name = "valera"

//
//        st.groups.add(group)

//        return ResponseEntity.ok(studyYearRepository.save(st))
        return ResponseEntity.ok(sty)
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