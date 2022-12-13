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
        fun addSubject(teacherId: Long, subjectId: Long): Teacher
    fun removeSubjectFromTeacher(teacherId: Long, subjectId: Long): Teacher
}
