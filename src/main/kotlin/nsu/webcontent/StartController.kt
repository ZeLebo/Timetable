package nsu.webcontent

import nsu.service.TimetableContentService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam


@Controller
class StartController(
    private val timetableService: TimetableContentService,
) {
    @GetMapping("/greetings")
    fun greeting(
        @RequestParam(name = "name", required = false, defaultValue = "World") name: String?,
        model: Model
    ): String {
        model.addAttribute("name", name)
        return "greetings"
    }

    @GetMapping("/time/{group}")
    fun timetable(@PathVariable group: String): ResponseEntity<*> {
        return try {
            return ResponseEntity.ok(timetableService.findSpecialGroup(group))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}