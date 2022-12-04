package nsu.controllers

import nsu.repository.FacultyRepository
import nsu.repository.SpecializationRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/faculty/")
class SpecializationController(
    private val specializationService: SpecializationRepository,
    private val facultyService: FacultyRepository,
) {
   // get all specializations
    @GetMapping
    fun getAllSpecializations(): ResponseEntity<*> {
        return ResponseEntity.ok(specializationService.findAll())
    }

    // get specializations by faculty
//    @GetMapping("/{facultyId}/specialization")
//    fun getAllSpecializationsByFaculty(@PathVariable facultyId: Int): ResponseEntity<*> {
//        return ResponseEntity.ok(specializationService.findBy)
//    }
}