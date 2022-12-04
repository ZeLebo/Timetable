package nsu.repository

import nsu.entities.people.Teacher
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherRepository : JpaRepository<Teacher, Long> {
    fun findByTeacherId(teacherId: Long): Teacher?
    fun findByName(name: String): Teacher?
}