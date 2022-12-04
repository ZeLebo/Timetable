package nsu.repository

import nsu.entities.university.Lesson
import nsu.entities.university.Room
import org.springframework.data.jpa.repository.JpaRepository

interface LessonRepository: JpaRepository<Lesson, Long> {
    fun findByLessonId(lessonId: Long): Lesson?
    fun findByName(name: String): Lesson?
}