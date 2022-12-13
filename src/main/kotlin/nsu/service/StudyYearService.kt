package nsu.service

import nsu.entities.people.Group
import nsu.entities.university.StudyYear
import nsu.entities.university.Subject

interface StudyYearService {
    fun addStudyYear(studyYear: StudyYear): StudyYear
    fun updateYear(studyYear: StudyYear): StudyYear
    fun delete(id: Long)
    fun findByID(id: Long): StudyYear?
    fun findByYearAndSpecializationName(year: Int, name: String): StudyYear?
    fun exists(id: Long): Boolean
    fun exists(year: Int, name: String): Boolean
    fun findAll(): List<StudyYear>
    fun updateYear(studyYearId: Long, studyYear: StudyYear): StudyYear
    fun addGroup(studyYearId: Long, group: Group): StudyYear
    fun addSubject(studyYearId: Long, subject: Subject): StudyYear
}