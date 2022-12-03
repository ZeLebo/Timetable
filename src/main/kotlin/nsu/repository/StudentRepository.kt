package nsu.repository

import nsu.entities.people.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Long> {
    fun findByFirstAndLast(first: String, last: String): Student?
    fun existsByFirstAndLast(first: String, last: String): Boolean
}