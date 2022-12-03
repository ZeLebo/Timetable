package nsu.controllers

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/add")
class AddController(
) {
    @RequestMapping("/")
    fun test(): String {
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @GetMapping("/teacher")
    fun showTeacherForm(): String {
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @PostMapping("/teacher")
    fun addTeacher(@RequestBody Teacher: nsu.entities.people.Teacher): String {
        // here's going to be the logic to collect everything from the system, and show here beautiful page
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @GetMapping("/subject")
    fun addSubject(): String {
        // here's going to be the logic to collect everything from the system, and show here beautiful page
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @GetMapping("/lesson")
    fun addLesson(): String {
        // here's going to be the logic to collect everything from the system, and show here beautiful page
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @GetMapping("/room")
    fun addRoom(): String {
        // here's going to be the logic to collect everything from the system, and show here beautiful page
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @GetMapping("/restriction")
    fun addRestriction(): String {
        // here's going to be the logic to collect everything from the system, and show here beautiful page
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }
}
