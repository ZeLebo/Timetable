package nsu.service

import nsu.entities.university.StudyYear

interface StudyYearService {
    fun addStudyYear(studyYear: StudyYear): StudyYear
    fun updateYear(studyYear: StudyYear): StudyYear
    fun delete(id: Long)
    fun findByID(id: Long): StudyYear?
    fun findByYear(number: Int): StudyYear?
    fun exists(id: Long): Boolean
    fun exists(number: Int): Boolean
    fun findAll(): List<StudyYear>
}