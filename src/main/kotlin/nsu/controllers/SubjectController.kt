package nsu.controllers

import nsu.entities.university.Subject
import nsu.service.StudyYearService
import nsu.service.SubjectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class SubjectController(
    private val subjectService: SubjectService,
    private val studyYearService: StudyYearService,
) {
    // get the list of all subjects
    @GetMapping("subject")
    fun getSubjects(): ResponseEntity<*> {
        return ResponseEntity.ok(subjectService.findAll())
    }

    // get specific subject
    @GetMapping("subject/{subjectId}")
    fun getSubject(@PathVariable subjectId: Int): ResponseEntity<*> {
        val subject = subjectService.findByID(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")
        return ResponseEntity.ok(subject)
    }

    // get the list of all subjects by studyYearId
    @GetMapping("studyYear/{studyYearId}/subject")
    fun getSubjectsByStudyYearId(@PathVariable studyYearId: Int): ResponseEntity<*> {
        val studyYear = studyYearService.findByID(studyYearId.toLong())
            ?: return ResponseEntity.badRequest().body("No such study year")
        return ResponseEntity.ok(studyYear.subjects)
    }

    // add new subject to specific study year
    @PostMapping("studyYear/{studyYearId}/subject")
    fun addSubject(@PathVariable studyYearId: Int, @RequestBody subject: Subject): ResponseEntity<*> {
        val studyYear = studyYearService.findByID(studyYearId.toLong())
            ?: return ResponseEntity.badRequest().body("No such study year")
        val newSubject = subjectService.addSubject(subject)
        studyYear.subjects.add(newSubject)
        val response = studyYearService.updateYear(studyYear)
        return ResponseEntity.ok(response)
    }

    // delete specific subject for specific study year
    @DeleteMapping("{studyYearId}/subject/{subjectId}")
    fun deleteSubject(@PathVariable studyYearId: Int, @PathVariable subjectId: Int): ResponseEntity<*> {
        val studyYear = studyYearService.findByID(studyYearId.toLong())
            ?: return ResponseEntity.badRequest().body("No such study year")
        val subject = subjectService.findByID(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")

        studyYear.subjects.remove(subject)
        studyYearService.updateYear(studyYear)

        return if (subjectService.findByID(subjectId.toLong()) == null) {
            ResponseEntity.ok("Subject was deleted successfully")
        } else {
            ResponseEntity.badRequest().body("Subject was not deleted")
        }
    }

    // update specific subject for specific study year
    @PatchMapping("{studyYearId}/subject/{subjectId}")
    fun updateSubject(@PathVariable studyYearId: Int, @PathVariable subjectId: Int, @RequestBody subject: Subject): ResponseEntity<*> {
        val studyYear = studyYearService.findByID(studyYearId.toLong())
            ?: return ResponseEntity.badRequest().body("No such study year")
        val oldSubject = subjectService.findByID(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")

        studyYear.subjects.remove(oldSubject)
        studyYear.subjects.add(subject)
        studyYearService.updateYear(studyYear)

        return if (subjectService.findByID(subjectId.toLong()) == subject) {
            ResponseEntity.ok("Subject was updated successfully")
        } else {
            ResponseEntity.badRequest().body("Subject was not updated")
        }
    }
}