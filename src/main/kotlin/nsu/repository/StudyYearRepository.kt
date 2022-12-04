package nsu.repository

import nsu.entities.university.StudyYear
import org.springframework.data.jpa.repository.JpaRepository

interface StudyYearRepository: JpaRepository<StudyYear, Long> {
    fun findByStudyYearId(studyYearId: Long): StudyYear?
    fun findByYear(year: Int): StudyYear?
}