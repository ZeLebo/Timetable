package nsu.algos.service

import nsu.algos.entities.Course

interface CourseService {
    fun addCourse(course: Course): Course
    fun updateCourse(course: Course): Course
    fun delete(id: Long)
    fun findByID(id: Long): Course?
    fun findByName(name: String): Course?
    fun exists(id: Long): Boolean
    fun exists(name: String): Boolean
    fun findAll(): List<Course>
    fun updateCourse(courseId: Long, course: Course): Course
}