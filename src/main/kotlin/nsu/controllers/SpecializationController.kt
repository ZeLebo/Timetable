package nsu.controllers

import nsu.entities.university.Specialization
import nsu.service.FacultyService
import nsu.service.SpecializationService
import nsu.service.StudyYearService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class SpecializationController(
    private val studyYearService: StudyYearService,
    private val specializationService: SpecializationService,
    private val facultyService: FacultyService,
) {
    // get all the specializations
    @GetMapping("specialization")
    fun getSpecializations(): ResponseEntity<*> {
        return ResponseEntity.ok(specializationService.findAll())
    }

    // get specific specialization
    @GetMapping("specialization/{specializationId}")
    fun getSpecialization(@PathVariable specializationId: Int): ResponseEntity<*> {
        val response = specializationService.findByID(specializationId.toLong())
            ?: return ResponseEntity.badRequest().body("Specialization with id $specializationId not found")
        return ResponseEntity.ok(response)
    }

    // get the list of all specializations for specific faculty
    @GetMapping("faculty/{facultyId}/specialization")
    fun getSpecializations(@PathVariable facultyId: Int): ResponseEntity<*> {
        val faculty = facultyService.findByID(facultyId.toLong())
            ?: return ResponseEntity.badRequest().body("No such faculty")
        return ResponseEntity.ok(faculty.specializations)
    }

    // add new specialization to specific faculty
    @PostMapping("faculty/{facultyId}/specialization")
    fun addSpecialization(@PathVariable facultyId: Int, @RequestBody request: Specialization): ResponseEntity<*> {
        val faculty = facultyService.findByID(facultyId.toLong())
            ?: return ResponseEntity.badRequest().body("No such faculty")

        val spec = specializationService.addSpecialization(request)
        faculty.specializations.add(spec)
        return ResponseEntity.ok( facultyService.updateFaculty(faculty))
    }

    // delete specific specialization for specific faculty
    @DeleteMapping("specialization/{specializationId}")
    fun deleteSpecialization(@PathVariable specializationId: Int): ResponseEntity<*> {
        val specialization = specializationService.findByID(specializationId.toLong())
            ?: return ResponseEntity.badRequest().body("No such specialization")
        specializationService.delete(specialization.specializationId)
        return if (specializationService.findByID(specializationId.toLong()) == null) {
            ResponseEntity.ok("Specialization was deleted successfully")
        } else {
            ResponseEntity.badRequest().body("Something went wrong")
        }
    }

    // update specific specialization for specific faculty
    @PatchMapping("specialization/{specializationId}")
    fun updateSpecialization(@PathVariable specializationId: Int, @RequestBody specialization: Specialization): ResponseEntity<*> {
        val specializationToUpdate = specializationService.findByID(specializationId.toLong())
            ?: return ResponseEntity.badRequest().body("No such specialization")
        specializationToUpdate.name = specialization.name

        specializationToUpdate.studyYears.map {
            it.specializationName = specialization.name
            studyYearService.updateYear(it)
        }

        specializationToUpdate.faculty = specialization.faculty
        specializationService.updateSpecialization(specializationToUpdate)
        return ResponseEntity.ok(specializationToUpdate)
    }
}