package nsu.algos.repository

import nsu.algos.entities.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository :JpaRepository<Course, Long>{
    fun findByName(name: String): Course?
    fun existsByName(name: String): Boolean
}