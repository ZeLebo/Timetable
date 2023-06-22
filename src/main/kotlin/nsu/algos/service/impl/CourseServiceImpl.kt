package nsu.algos.service.impl


import nsu.algos.entities.Course
import nsu.algos.repository.CourseRepository
import nsu.algos.service.CourseService
import org.springframework.stereotype.Service


@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository
): CourseService {
    override fun addCourse(course: Course): Course {
        if (courseRepository.findByName(course.name) != null) {
            throw RuntimeException("Lesson already exists")
        }
        return courseRepository.save(course)
    }

    override fun updateCourse(course: Course): Course {
        return courseRepository.save(course)
    }

    override fun updateCourse(id: Long, course: Course): Course {
        val lessonDb = this.findByID(id)
            ?: throw RuntimeException("Lesson not found")
        lessonDb.name = course.name
        lessonDb.type = course.type
        return this.updateCourse(lessonDb)
    }

    override fun delete(id: Long) {
        // check if lesson exists
        courseRepository.findById(id).orElse(null) ?: throw RuntimeException("Lesson not found")
        courseRepository.deleteById(id)

        if (courseRepository.findById(id).orElse(null) != null) {
            throw RuntimeException("Lesson not deleted")
        }
    }

    override fun findByID(id: Long): Course? {
        return courseRepository.findById(id).orElse(null)
    }

    override fun findByName(name: String): Course? {
        return courseRepository.findByName(name)
    }
    override fun exists(id: Long): Boolean {
        return courseRepository.existsById(id)
    }

    override fun exists(name: String): Boolean {
        return courseRepository.findByName(name) != null
    }

    override fun findAll(): List<Course> {
        return courseRepository.findAll()
    }
}