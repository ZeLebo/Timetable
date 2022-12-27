package nsu.controllers

import nsu.entities.university.Specialization
import nsu.entities.university.StudyYear
import nsu.service.SpecializationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
/**
 * Controller for Sp
 *
 * @param roomService - service logic for work with room entity
 */
@RestController
@RequestMapping("/api/v1")
class SpecializationController(
    private val specializationService: SpecializationService,
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

    // delete specific specialization for specific faculty
    @DeleteMapping("specialization/{specializationId}")
    fun deleteSpecialization(@PathVariable specializationId: Int): ResponseEntity<*> {
        return try {
            specializationService.delete(specializationId.toLong())
            ResponseEntity.ok("Specialization with id $specializationId deleted")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // update specific specialization
    @PatchMapping("specialization/{specializationId}")
    fun updateSpecialization(@PathVariable specializationId: Int, @RequestBody specialization: Specialization): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(specializationService.updateSpecialization(specializationId.toLong(), specialization))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // get the list of all studyYears by specializationId
    @GetMapping("specialization/{specializationId}/studyYear")
    fun getStudyYearsBySpecializationId(@PathVariable specializationId: Int): ResponseEntity<*> {
        val specialization = specializationService.findByID(specializationId.toLong())
            ?: return ResponseEntity.badRequest().body("No such specialization")
        return ResponseEntity.ok(specialization.studyYears)
    }

    // add new study year to specific specialization
    @PostMapping("specialization/{specializationId}studyYear")
    fun addStudyYear(@PathVariable specializationId: Int, @RequestBody request: StudyYear): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(specializationService.addStudyYearToSpecialization(specializationId.toLong(), request))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}
