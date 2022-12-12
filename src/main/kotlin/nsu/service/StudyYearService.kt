package nsu.service

import nsu.entities.university.StudyYear

interface StudyYearService {
    fun addStudyYear(studyYear: StudyYear): StudyYear
    fun updateYear(studyYear: StudyYear): StudyYear
    fun delete(id: Long)
    fun findByID(id: Long): StudyYear?
    fun findByYearAndSpecializationName(year: Int, name: String): StudyYear?
    fun exists(id: Long): Boolean
    fun exists(year: Int, name: String): Boolean
    fun findAll(): List<StudyYear>
}