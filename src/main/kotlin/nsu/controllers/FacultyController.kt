package nsu.controllers

import nsu.entities.university.Faculty
import nsu.entities.university.Specialization
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
        return try {
            ResponseEntity.ok(facultyService.findByID(facultyId.toLong()))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // delete faculty
    @DeleteMapping("faculty/{facultyId}")
    fun deleteFaculty(@PathVariable facultyId: Int): ResponseEntity<*> {
        return try {
            facultyService.delete(facultyId.toLong())
            ResponseEntity.ok("Faculty was deleted successfully")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // update faculty
    @PatchMapping("faculty/{facultyId}")
    fun updateFaculty(@PathVariable facultyId: Int, @RequestBody faculty: Faculty): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(facultyService.updateFaculty(facultyId, faculty))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // add new specialization to specific faculty
    @PostMapping("faculty/{facultyId}/specialization")
    fun addSpecialization(@PathVariable facultyId: Int, @RequestBody specialization: Specialization): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(facultyService.addSpecialization(facultyId.toLong(), specialization))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // get the list of all specializations for specific faculty
    @GetMapping("faculty/{facultyId}/specialization")
    fun getSpecializations(@PathVariable facultyId: Int): ResponseEntity<*> {
        return try {
            val faculty = facultyService.findByID(facultyId.toLong())
                ?: return ResponseEntity.badRequest().body("Faculty with id $facultyId not found")
            ResponseEntity.ok(faculty.specializations)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}