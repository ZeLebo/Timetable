package nsu.controllers

import nsu.entities.university.Faculty
import nsu.entities.university.Specialization
import nsu.service.FacultyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Controller for faculty
 *
 * @param facultyService - service logic for work with faculty entity
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class FacultyController(
    private val facultyService: FacultyService,
) {

    /**
     * This method get the list of all faculties
     *
     * @return list of all faculties
     */
    @GetMapping("faculty")
    fun getFaculties(): ResponseEntity<*> {
        return ResponseEntity.ok(facultyService.findAll())
    }

    /**
     * This method add faculty
     *
     * @param faculty - name of the faculty
     */

    @PostMapping("faculty")
    fun addFaculty(@RequestBody faculty: Faculty): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(facultyService.addFaculty(faculty))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    /**
     * This method get specific faculty
     *
     * @return faculty by id
     */

    @GetMapping("faculty/{facultyId}")
    fun getFaculty(@PathVariable facultyId: Int): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(facultyService.findByID(facultyId.toLong()))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    /**
     * This method delete faculty by id
     *
     */

    @DeleteMapping("faculty/{facultyId}")
    fun deleteFaculty(@PathVariable facultyId: Int): ResponseEntity<*> {
        return try {
            facultyService.delete(facultyId.toLong())
            ResponseEntity.ok("Faculty was deleted successfully")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }


    /**
     * This method update faculty's id and name
     */

    @PatchMapping("faculty/{facultyId}")
    fun updateFaculty(@PathVariable facultyId: Int, @RequestBody faculty: Faculty): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(facultyService.updateFaculty(facultyId, faculty))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    /**
     * This method add specialization to specific faculty by id
     *
     */

    @PostMapping("faculty/{facultyId}/specialization")
    fun addSpecialization(@PathVariable facultyId: Int, @RequestBody specialization: Specialization): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(facultyService.addSpecialization(facultyId.toLong(), specialization))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    /**
     * This method get the list of all specializations for specific faculty
     *
     */
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