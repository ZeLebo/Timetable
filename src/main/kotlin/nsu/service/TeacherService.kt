package nsu.service

import nsu.entities.people.Teacher

interface TeacherService {
    fun addTeacher(teacher: Teacher): Teacher
    fun updateTeacher(teacher: Teacher): Teacher
    fun delete(id: Long)
    fun findByID(id: Long): Teacher?
    fun findByName(name: String): Teacher?
    fun exists(id: Long): Boolean
    fun exists(name: String): Boolean
    fun findAll(): List<Teacher>
    fun updateTeacher(teacherId: Long, teacher: Teacher): Teacher
    fun addLesson(teacherId: Long, subjectId: Long): Teacher
    fun removeLessonFromTeacher(teacherId: Long, subjectId: Long): Teacher
}
