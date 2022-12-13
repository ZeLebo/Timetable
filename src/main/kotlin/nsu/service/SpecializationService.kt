package nsu.service

import nsu.entities.university.Specialization
import nsu.entities.university.StudyYear

interface SpecializationService {

    fun addSpecialization(specialization: Specialization): Specialization
    fun updateSpecialization(specialization: Specialization): Specialization
    fun delete(id: Long)
    fun findByID(id: Long): Specialization?
    fun findByName(name: String): Specialization?
    fun exists(id: Long): Boolean
    fun exists(name: String): Boolean
    fun findAll(): List<Specialization>
    fun updateSpecialization(specializationId: Long, specialization: Specialization): Specialization
    fun addStudyYearToSpecialization(specializationId: Long, studyYear: StudyYear): Specialization
    fun removeStudyYearFromSpecialization(specializationId: Long, studyYearId: Long): Specialization
}