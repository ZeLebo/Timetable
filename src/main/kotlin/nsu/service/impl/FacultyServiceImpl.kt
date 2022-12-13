package nsu.service.impl


import nsu.entities.university.Faculty
import nsu.entities.university.Specialization
import nsu.repository.FacultyRepository

import nsu.service.FacultyService

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class FacultyServiceImpl(
    private val facultyRepository: FacultyRepository,
    private val specializationService: SpecializationServiceImpl,
): FacultyService {
    @Transactional
    override fun addFaculty(faculty: Faculty): Faculty {
        if (facultyRepository.findByName(faculty.name) != null) {
            throw RuntimeException("Faculty already exist")
        }

        val facultyDb = facultyRepository.save(faculty)

        facultyDb.specializations = faculty.specializations.map {
            specializationService.addSpecialization(
                Specialization(
                    it.name,
                    facultyDb,
                    it.studyYears
                )
            )
        }.toMutableList()

        return facultyRepository.save(faculty)
    }

    override fun updateFaculty(faculty: Faculty): Faculty {
        return facultyRepository.save(faculty)
    }

    override fun updateFaculty(id: Int, faculty: Faculty): Faculty {
        val facultyDb = facultyRepository.findByFacultyId(id.toLong()) ?: throw RuntimeException("Faculty not found")
        facultyDb.name = faculty.name
        facultyDb.specializations = faculty.specializations
        return updateFaculty(facultyDb)
    }

    override fun delete(id: Long) {
        // check if faculty exists
        facultyRepository.findByFacultyId(id) ?: throw RuntimeException("Faculty not found")
        facultyRepository.deleteById(id)
        // if faculty exists, throw exception
        if (facultyRepository.findByFacultyId(id) != null) {
            throw RuntimeException("Faculty not deleted")
        }
    }

    override fun addSpecialization(facultyId: Long, specialization: Specialization): Faculty {
        val facultyDb = facultyRepository.findByFacultyId(facultyId) ?: throw RuntimeException("Faculty not found")
        try {
            val specializationDb = specializationService.addSpecialization(specialization)
            facultyDb.specializations.add(specializationDb)
            return updateFaculty(facultyDb)
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
    }

    override fun deleteSpecialization(facultyId: Long, specializationId: Long) {
        val facultyDb = facultyRepository.findByFacultyId(facultyId) ?: throw RuntimeException("Faculty not found")
        val specializationDb = specializationService.findByID(specializationId)
            ?: throw RuntimeException("Specialization not found")
        facultyDb.specializations.remove(specializationDb)
        facultyRepository.save(facultyDb)
        specializationService.delete(specializationId)
    }

    override fun findByName(name: String): Faculty? {
        return facultyRepository.findByName(name) ?: throw RuntimeException("Faculty not found")
    }

    override fun findByID(id: Long): Faculty? {
        return facultyRepository.findByFacultyId(id) ?: throw RuntimeException("Faculty not found")
    }

    override fun exists(id: Long): Boolean {
        return facultyRepository.existsById(id)
    }

    override fun exists(name: String): Boolean {
        return facultyRepository.findByName(name) != null
    }

    override fun findAll(): List<Faculty> {
        return facultyRepository.findAll()
    }
}