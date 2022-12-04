package nsu.controllers

import nsu.entities.university.StudyYear
import nsu.repository.SpecializationRepository
import nsu.repository.StudyYearRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class StudyYearController(
    private val studyYearService: StudyYearRepository,
    private val specializationService: SpecializationRepository,
) {
    // get the list of all study years
    @GetMapping("studyYear")
    fun getStudyYears(): ResponseEntity<*> {
        return ResponseEntity.ok(studyYearService.findAll())
    }

    // get specific study year
    @GetMapping("studyYear/{studyYearId}")
    fun getStudyYear(@PathVariable studyYearId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(studyYearService.findById(studyYearId.toLong()))
    }

    // get the list of all studyYears by specializationId
    @GetMapping("specialization/{specializationId}/studyYear")
    fun getStudyYearsBySpecializationId(@PathVariable specializationId: Int): ResponseEntity<*> {
        val specialization = specializationService.findBySpecializationId(specializationId.toLong())
            ?: return ResponseEntity.badRequest().body("No such specialization")
        return ResponseEntity.ok(specialization.studyYears)
    }

    // add new study year to specific specialization
    @PostMapping("specialization/{specializationId}studyYear")
    fun addStudyYear(@PathVariable specializationId: Int, @RequestBody studyYear: StudyYear): ResponseEntity<*> {
        val specialization = specializationService.findBySpecializationId(specializationId.toLong())
            ?: return ResponseEntity.badRequest().body("No such specialization")
        specialization.studyYears.add(studyYear)
        specializationService.save(specialization)
        return ResponseEntity.ok(studyYear)
    }

    // delete specific study year for specific specialization
    @DeleteMapping("studyYear/{studyYearId}")
    fun deleteStudyYear(@PathVariable studyYearId: Int): ResponseEntity<*> {
        val studyYear = studyYearService.findByStudyYearId(studyYearId.toLong())
            ?: return ResponseEntity.badRequest().body("No such study year")

        studyYearService.delete(studyYear)

        return if (studyYearService.findByStudyYearId(studyYearId.toLong()) == null) {
            ResponseEntity.ok("Study year was deleted successfully")
        } else {
            ResponseEntity.badRequest().body("Study year was not deleted")
        }
    }

    // update specific study year for specific specialization
    @PatchMapping("studyYear/{studyYearId}")
    fun updateStudyYear(@PathVariable studyYearId: Int, @RequestBody studyYear: StudyYear): ResponseEntity<*> {
        val studyYearToUpdate = studyYearService.findByStudyYearId(studyYearId.toLong())
            ?: return ResponseEntity.badRequest().body("No such study year")
        studyYearToUpdate.year = studyYear.year
        return ResponseEntity.ok(studyYearService.save(studyYearToUpdate))
    }
}