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

    override fun updateLesson(id: Long, lesson: Lesson): Lesson {
        val lessonDb = this.findByID(id)
            ?: throw RuntimeException("Lesson not found")
        lessonDb.name = lesson.name
        lessonDb.roomType = lesson.roomType
        return this.updateLesson(lessonDb)
    }

    override fun delete(id: Long) {
        // check if lesson exists
        lessonRepository.findById(id).orElse(null) ?: throw RuntimeException("Lesson not found")
        lessonRepository.deleteById(id)

        if (lessonRepository.findById(id).orElse(null) != null) {
            throw RuntimeException("Lesson not deleted")
        }
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