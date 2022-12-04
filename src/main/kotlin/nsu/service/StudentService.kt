package nsu.service

import nsu.entities.people.Student

interface StudentService {
    fun addStudent(student: Student): Student
    fun updateStudent(student: Student): Student
    fun delete(id: Long)
    fun findByID(id: Long): Student?
    fun findByName(name: String): Student?
    fun exists(id: Long): Boolean
    fun exists(name: String): Boolean
    fun findAll(): List<Student>
}