package nsu.repository

import nsu.entities.university.Specialization
import org.springframework.data.jpa.repository.JpaRepository

interface SpecializationRepository: JpaRepository<Specialization, Long> {
    fun findBySpecializationId(specializationId: Long): Specialization?
    fun findByName(name: String): Specialization?
}