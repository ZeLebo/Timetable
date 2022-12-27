package nsu.webcontent

import nsu.controllers.StudentController
import nsu.entities.people.Group
import nsu.entities.people.Student
import nsu.service.TimetableContentService
import nsu.service.impl.StudyYearServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
class StartController(
    private val timetableService: TimetableContentService,
    private val studentController: StudentController,
    private val studyYearServiceImpl: StudyYearServiceImpl,
) {
    @GetMapping("/greetings")
    fun greeting(
        @RequestParam(name = "name", required = false, defaultValue = "World") name: String?,
        model: Model
    ): String {
        model.addAttribute("name", name)
        return "greetings"
    }

    @RequestMapping("/form", method = [RequestMethod.GET])
    fun form(model: Model): String {
        model.addAttribute("student", (listOf<Student>()))
        return "form"
    }

    @GetMapping("/time/{group}")
    fun timetable(@PathVariable group: String): ResponseEntity<*> {
        return try {
            return ResponseEntity.ok(timetableService.findSpecialGroup(group))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("studyYear/{studyYearId}/group")
    fun addGroup(@RequestBody request: Group, @PathVariable studyYearld: Int, @PathVariable studyYearId: String): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(studyYearServiceImpl.addGroup(studyYearId.toLong(), request))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}
