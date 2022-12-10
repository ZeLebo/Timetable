package nsu.service.impl

import nsu.entities.university.Lesson
import nsu.repository.LessonRepository
import nsu.service.LessonService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class LessonServiceImpl(
    private val lessonRepository: LessonRepository
): LessonService {
    override fun addLesson(lesson: Lesson): Lesson {
        if (lessonRepository.findByName(lesson.name) != null) {
            throw RuntimeException("Lesson already exists")
        }
        return lessonRepository.save(lesson)
    }

    override fun updateLesson(lesson: Lesson): Lesson {
        return lessonRepository.save(lesson)
    }

    override fun delete(id: Long) {
        lessonRepository.deleteById(id)
    }

    override fun findByID(id: Long): Lesson? {
        return lessonRepository.findById(id).orElse(null)
    }

    override fun findByName(name: String): Lesson? {
        return lessonRepository.findByName(name)
    }
    override fun exists(id: Long): Boolean {
        return lessonRepository.existsById(id)
    }

    override fun exists(name: String): Boolean {
        return lessonRepository.findByName(name) != null
    }

    override fun findAll(): List<Lesson> {
        return lessonRepository.findAll()
    }
}