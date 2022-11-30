package nsu.repository

import nsu.entities.people.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long> {
    fun existsByFirstLastName(firstname: String, lastname: String): Boolean
    fun findByFirstLastName(firstname: String, lastname: String): Student?
    fun getIdByFirstLastName(firstname: String, lastname: String): Long?
}