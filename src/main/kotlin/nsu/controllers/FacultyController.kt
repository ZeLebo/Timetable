package nsu.controllers

import nsu.entities.university.Faculty
import nsu.repository.FacultyRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class FacultyController(
    private val facultyService: FacultyRepository,
) {
    //get the list of all faculties
    @GetMapping("faculty")
    fun getFaculties(): ResponseEntity<*> {
        return ResponseEntity.ok(facultyService.findAll())
    }

    // add faculty
    @PostMapping("faculty")
    fun addFaculty(@RequestBody faculty: Faculty): ResponseEntity<*> {
        return ResponseEntity.ok(facultyService.save(faculty))
    }

    // get specific faculty
    @GetMapping("faculty/{facultyId}")
    fun getFaculty(@PathVariable facultyId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(facultyService.findById(facultyId.toLong()))
    }

    // delete faculty
    @DeleteMapping("faculty/{facultyId}")
    fun deleteFaculty(@PathVariable facultyId: Int): ResponseEntity<*> {
        facultyService.deleteById(facultyId.toLong())
        return ResponseEntity.ok("Faculty was deleted successfully")
    }

    // update faculty
    @PatchMapping("faculty/{facultyId}")
    fun updateFaculty(@PathVariable facultyId: Int, @RequestBody faculty: Faculty): ResponseEntity<*> {
        val facultyToUpdate = facultyService.findByFacultyId(facultyId.toLong())
            ?: return ResponseEntity.badRequest().body("No such faculty")
        facultyToUpdate.name = faculty.name
        if (faculty.specializations.isNotEmpty()) {
            facultyToUpdate.specializations = faculty.specializations
        }
        return ResponseEntity.ok(facultyService.save(facultyToUpdate))
    }
}