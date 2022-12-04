package nsu.controllers.timetable

import org.springframework.web.bind.annotation.*

// here are the request mapping
@RestController
@RequestMapping("/api/v1/timetable")
class TimetableController {
    @GetMapping("/")
    fun test(): String {
        return "The server is working, soon at this page you will be able to see all the buttons to pass the info"
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