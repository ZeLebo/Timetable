package nsu.service

import nsu.entities.university.Lesson
import nsu.entities.university.Subject

interface SubjectService {
    fun addSubject(subject: Subject): Subject
    fun updateSubject(subject: Subject): Subject
    fun delete(id: Long)
    fun findByID(id: Long): Subject?
    fun findByName(name: String): Subject?
    fun exists(id: Long): Boolean

    fun findAll(): List<Subject>
    fun updateSubject(subjectId: Long, subject: Subject): Subject
    fun addLesson(subjectId: Long, lesson: Lesson): Lesson
}