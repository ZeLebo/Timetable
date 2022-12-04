package nsu.controllers

import nsu.entities.university.Subject
import nsu.repository.StudyYearRepository
import nsu.repository.SubjectRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class SubjectController(
    private val subjectService: SubjectRepository,
    private val studyYearService: StudyYearRepository,
) {
    // get the list of all subjects
    @GetMapping("subject")
    fun getSubjects(): ResponseEntity<*> {
        return ResponseEntity.ok(subjectService.findAll())
    }

    // get specific subject
    @GetMapping("subject/{subjectId}")
    fun getSubject(@PathVariable subjectId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(subjectService.findBySubjectId(subjectId.toLong()))
    }

    // get the list of all subjects by studyYearId
    @GetMapping("studyYear/{studyYearId}/subject")
    fun getSubjectsByStudyYearId(@PathVariable studyYearId: Int): ResponseEntity<*> {
        val studyYear = studyYearService.findByStudyYearId(studyYearId.toLong())
            ?: return ResponseEntity.badRequest().body("No such study year")
        return ResponseEntity.ok(studyYear.subjects)
    }

    // add new subject to specific study year
    @PostMapping("studyYear/{studyYearId}/subject")
    fun addSubject(@PathVariable studyYearId: Int, @RequestBody subject: Subject): ResponseEntity<*> {
        val studyYear = studyYearService.findByStudyYearId(studyYearId.toLong())
            ?: return ResponseEntity.badRequest().body("No such study year")
        studyYear.subjects.add(subject)
        studyYearService.save(studyYear)
        return ResponseEntity.ok(subject)
    }

    // delete specific subject for specific study year
    @DeleteMapping("{studyYearId}/subject/{subjectId}")
    fun deleteSubject(@PathVariable studyYearId: Int, @PathVariable subjectId: Int): ResponseEntity<*> {
        val studyYear = studyYearService.findByStudyYearId(studyYearId.toLong())
            ?: return ResponseEntity.badRequest().body("No such study year")
        val subject = subjectService.findBySubjectId(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")

        studyYear.subjects.remove(subject)
        studyYearService.save(studyYear)

        return if (subjectService.findBySubjectId(subjectId.toLong()) == null) {
            ResponseEntity.ok("Subject was deleted successfully")
        } else {
            ResponseEntity.badRequest().body("Subject was not deleted")
        }
    }

    // update specific subject for specific study year
    @PatchMapping("{studyYearId}/subject/{subjectId}")
    fun updateSubject(@PathVariable studyYearId: Int, @PathVariable subjectId: Int, @RequestBody subject: Subject): ResponseEntity<*> {
        val studyYear = studyYearService.findByStudyYearId(studyYearId.toLong())
            ?: return ResponseEntity.badRequest().body("No such study year")
        val oldSubject = subjectService.findBySubjectId(subjectId.toLong())
            ?: return ResponseEntity.badRequest().body("No such subject")

        studyYear.subjects.remove(oldSubject)
        studyYear.subjects.add(subject)
        studyYearService.save(studyYear)

        return if (subjectService.findBySubjectId(subjectId.toLong()) == subject) {
            ResponseEntity.ok("Subject was updated successfully")
        } else {
            ResponseEntity.badRequest().body("Subject was not updated")
        }
    }
}