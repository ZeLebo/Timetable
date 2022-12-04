package nsu.controllers

import nsu.entities.university.Specialization
import nsu.repository.FacultyRepository
import nsu.repository.SpecializationRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class SpecializationController(
    private val specializationService: SpecializationRepository,
    private val facultyService: FacultyRepository,
) {
    // get all the specializations
    @GetMapping("specialization")
    fun getSpecializations(): ResponseEntity<*> {
        return ResponseEntity.ok(specializationService.findAll())
    }

    // get specific specialization
    @GetMapping("specialization/{specializationId}")
    fun getSpecialization(@PathVariable specializationId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(specializationService.findBySpecializationId(specializationId.toLong()))
    }

    // get the list of all specializations for specific faculty
    @GetMapping("faculty/{facultyId}/specialization")
    fun getSpecializations(@PathVariable facultyId: Int): ResponseEntity<*> {
        val faculty = facultyService.findByFacultyId(facultyId.toLong())
            ?: return ResponseEntity.badRequest().body("No such faculty")
        return ResponseEntity.ok(faculty.specializations)
    }

    // add new specialization to specific faculty
    @PostMapping("faculty/{facultyId}/specialization")
    fun addSpecialization(@PathVariable facultyId: Int, @RequestBody specialization: Specialization): ResponseEntity<*> {
        val faculty = facultyService.findByFacultyId(facultyId.toLong())
            ?: return ResponseEntity.badRequest().body("No such faculty")
        faculty.specializations.add(specialization)
        facultyService.save(faculty)
        return ResponseEntity.ok(specialization)
    }

    // delete specific specialization for specific faculty
    @DeleteMapping("specialization/{specializationId}")
    fun deleteSpecialization(@PathVariable specializationId: Int): ResponseEntity<*> {
        val specialization = specializationService.findBySpecializationId(specializationId.toLong())
            ?: return ResponseEntity.badRequest().body("No such specialization")
        specializationService.delete(specialization)
        return if (specializationService.findBySpecializationId(specializationId.toLong()) == null) {
            ResponseEntity.ok("Specialization was deleted successfully")
        } else {
            ResponseEntity.badRequest().body("Something went wrong")
        }
    }

    // update specific specialization for specific faculty
    @PatchMapping("specialization/{specializationId}")
    fun updateSpecialization(@PathVariable specializationId: Int, @RequestBody specialization: Specialization): ResponseEntity<*> {
        val specializationToUpdate = specializationService.findBySpecializationId(specializationId.toLong())
            ?: return ResponseEntity.badRequest().body("No such specialization")
        specializationToUpdate.name = specialization.name
        specializationToUpdate.faculty = specialization.faculty
        specializationService.save(specializationToUpdate)
        return ResponseEntity.ok(specializationToUpdate)
    }
}