package nsu.service.impl

import nsu.entities.university.Specialization
import nsu.repository.SpecializationRepository
import nsu.service.SpecializationService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class SpecializationImpl (
    private val specializationRepository: SpecializationRepository
): SpecializationService {
    override fun addSpecialization(specialization: Specialization): Specialization {
        if (specializationRepository.findByName(specialization.name) != null) {
            throw RuntimeException("Specialization already exists")
        }
        return specializationRepository.save(specialization)
    }

    override fun updateSpecialization(specialization: Specialization): Specialization {
        return specializationRepository.save(specialization)
    }

    override fun delete(id: Long) {
        specializationRepository.deleteById(id)
    }

    override fun findByID(id: Long): Specialization? {
        return specializationRepository.findById(id).orElse(null)
    }

    override fun findByName(name: String): Specialization? {
        return specializationRepository.findByName(name)
    }
    override fun exists(id: Long): Boolean {
        return specializationRepository.existsById(id)
    }

    override fun exists(name: String): Boolean {
        return specializationRepository.findByName(name) != null
    }

    override fun findAll(): List<Specialization> {
        return specializationRepository.findAll()
    }
}