package nsu.service.impl


import nsu.entities.university.Faculty
import nsu.entities.university.Specialization
import nsu.repository.FacultyRepository

import nsu.service.FacultyService

import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class FacultyServiceImpl(
    private val facultyRepository: FacultyRepository,
    private val specializationService: SpecializationServiceImpl,
): FacultyService {
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

    override fun delete(id: Long) {
        facultyRepository.deleteById(id)
    }

    override fun findByName(name: String): Faculty? {
        return facultyRepository.findByName(name)
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