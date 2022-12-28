package nsu.controllers

import nsu.entities.people.Group
import nsu.entities.university.StudyYear
import nsu.entities.university.Subject
import nsu.service.StudyYearService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
/**
 * Controller for Study year
 *
 * @param studyYearService - service logic for work with study year entity
 */
@RestController
@RequestMapping("/api/v1")
class StudyYearController(
    private val studyYearService: StudyYearService,
) {
    /**
     * This method get the list of all Study years
     *
     * @return list of all Study years
     */
    @GetMapping("studyYear")
    fun getStudyYears(): ResponseEntity<*> {
        return ResponseEntity.ok(studyYearService.findAll())
    }
    /**
     * This method get specific study year
     *
     */

    @GetMapping("studyYear/{studyYearId}")
    fun getStudyYear(@PathVariable studyYearId: Int): ResponseEntity<*> {
        val studyYear = studyYearService.findByID(studyYearId.toLong())
            ?: return ResponseEntity.badRequest().body("No such study year")
        return ResponseEntity.ok(studyYear)
    }
    /**
     * This method get the list of all groups for specific study year
     *
     */

    @GetMapping("studyYear/{studyYearId}/group")
    fun getGroupsForStudyYear(@PathVariable studyYearId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(studyYearService.findByID(studyYearId.toLong()))
    }
    /**
     * This method add new group to specific study year
     *
     */
    @PostMapping("studyYear/{studyYearId}/group")
    fun addGroup(@RequestBody request: Group, @PathVariable studyYearId: Int): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(studyYearService.addGroup(studyYearId.toLong(), request))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
    /**
     * This method get the list of all subjects by studyYearId
     *
     */

    @GetMapping("studyYear/{studyYearId}/subject")
    fun getSubjectsByStudyYearId(@PathVariable studyYearId: Int): ResponseEntity<*> {
        val studyYear = studyYearService.findByID(studyYearId.toLong())
            ?: return ResponseEntity.badRequest().body("No such study year")
        return ResponseEntity.ok(studyYear.subjects)
    }
    /**
     * This method add new subject to specific study year
     *
     */

    @PostMapping("studyYear/{studyYearId}/subject")
    fun addSubject(@PathVariable studyYearId: Int, @RequestBody subject: Subject): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(studyYearService.addSubject(studyYearId.toLong(), subject))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
    /**
     * This method delete specific study year
     *
     */

    @DeleteMapping("studyYear/{studyYearId}")
    fun deleteStudyYear(@PathVariable studyYearId: Int): ResponseEntity<*> {
        return try {
            studyYearService.delete(studyYearId.toLong())
            ResponseEntity.ok("Study year was deleted successfully")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("No such study year")
        }
    }
    /**
     * This method update specific study year for specific specialization
     *
     */

    @PatchMapping("studyYear/{studyYearId}")
    fun updateStudyYear(@PathVariable studyYearId: Int, @RequestBody studyYear: StudyYear): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(studyYearService.updateYear(studyYearId.toLong(), studyYear))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}