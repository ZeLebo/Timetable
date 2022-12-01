package nsu.repository

import nsu.entities.people.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Long> {
    fun findByFirstAndLast(first: String, last: String): Student?
    fun existsByFirstAndLast(first: String, last: String): Boolean
    fun findIdByFirstAndLast(first: String, last: String): Long?
//    fun existsByfirstnameAndlastname(Firstname: String, Lastname: String): Boolean
//    fun findByFirstLastName(firstname: String, lastname: String): Student?
//    fun getIdByFirstLastName(firstname: String, lastname: String): Long?
}