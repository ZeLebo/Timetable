package nsu.controllers

import nsu.entities.university.Faculty
import nsu.repository.FacultyRepository
import nsu.service.FacultyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class FacultyController(
    private val facultyService: FacultyService,
) {
    //get the list of all faculties
    @GetMapping("faculty")
    fun getFaculties(): ResponseEntity<*> {
        return ResponseEntity.ok(facultyService.findAll())
    }

    // add faculty
    @PostMapping("faculty")
    fun addFaculty(@RequestBody faculty: Faculty): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(facultyService.addFaculty(faculty))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // get specific faculty
    @GetMapping("faculty/{facultyId}")
    fun getFaculty(@PathVariable facultyId: Int): ResponseEntity<*> {
        // check the existence of faculty
        val faculty = facultyService.findByID(facultyId.toLong())
            ?: return ResponseEntity.badRequest().body("No such faculty")

        return ResponseEntity.ok(faculty)
    }

    // delete faculty
    @DeleteMapping("faculty/{facultyId}")
    fun deleteFaculty(@PathVariable facultyId: Int): ResponseEntity<*> {
        // check if exists
        facultyService.findByID(facultyId.toLong()) ?: return ResponseEntity.badRequest().body("No such faculty")
        facultyService.delete(facultyId.toLong())
        return if (facultyService.findByID(facultyId.toLong()) == null) {
            ResponseEntity.ok("Faculty was deleted successfully")
        } else {
            ResponseEntity.badRequest().body("Something went wrong")
        }
    }

    // update faculty
    @PatchMapping("faculty/{facultyId}")
    fun updateFaculty(@PathVariable facultyId: Int, @RequestBody faculty: Faculty): ResponseEntity<*> {
        val facultyToUpdate = facultyService.findByID(facultyId.toLong())
            ?: return ResponseEntity.badRequest().body("No such faculty")
        facultyToUpdate.name = faculty.name
        if (faculty.specializations.isNotEmpty()) {
            facultyToUpdate.specializations = faculty.specializations
        }
        return ResponseEntity.ok(facultyService.updateFaculty(facultyToUpdate))
    }
}