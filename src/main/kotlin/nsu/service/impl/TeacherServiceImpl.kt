package nsu.service.impl

import nsu.repository.TeacherRepository
import org.springframework.stereotype.Service
import nsu.entities.people.Teacher
import nsu.entities.university.Lesson
import nsu.service.LessonService
import nsu.service.TeacherService
import java.lang.RuntimeException

@Service
class TeacherServiceImpl(
    private val teacherRepository: TeacherRepository,
    private val lessonService: LessonService,
):  TeacherService {
    override fun addTeacher(teacher: Teacher): Teacher {
        if (teacherRepository.findByName(teacher.name) != null) {
            throw RuntimeException("Teacher already exists")
        }

        val teacherDb = teacherRepository.save(teacher)

        teacherDb.lessons = teacher.lessons.map {
            lessonService.addLesson(
                Lesson(
                    it.name,
                    it.roomType,
                    it.subjectType,
                    it.subject,
                    teacherDb
                )
            )
        }.toMutableList()

    return teacherRepository.save(teacher)
}
    override fun updateTeacher(teacher: Teacher): Teacher {
        return teacherRepository.save(teacher)
    }

    override fun updateTeacher(teacherId: Long, teacher: Teacher): Teacher {
        val teacherDb = this.findByID(teacherId)
            ?: throw RuntimeException("Teacher not found")
        teacherDb.name = teacher.name
        teacherDb.lessons = teacher.lessons
        return this.updateTeacher(teacherDb)
    }

    override fun delete(id: Long) {
        this.findByID(id) ?: throw RuntimeException("Teacher not found")
        teacherRepository.deleteById(id)

        if (this.exists(id)) {
            throw RuntimeException("Teacher not deleted")
        }
    }

    override fun addLesson(teacherId: Long, subjectId: Long): Teacher {
        val teacherDb = this.findByID(teacherId)
            ?: throw RuntimeException("Teacher not found")
        val lessonDb = lessonService.findByID(subjectId)
            ?: throw RuntimeException("Lesson not found")

        lessonDb.teacher = teacherDb
        teacherDb.lessons.add(lessonDb)

        lessonService.updateLesson(lessonDb)
        return this.updateTeacher(teacherDb)
    }

    override fun removeLessonFromTeacher(teacherId: Long, subjectId: Long): Teacher {
        val teacherDb = this.findByID(teacherId)
            ?: throw RuntimeException("Teacher not found")
        val lessonDb = lessonService.findByID(subjectId)
            ?: throw RuntimeException("Lesson not found")

        teacherDb.lessons.remove(lessonDb)
        return this.updateTeacher(teacherDb)
    }

    override fun findByID(id: Long): Teacher? {
        return teacherRepository.findById(id).orElse(null)
    }

    override fun findByName(name: String): Teacher? {
        return teacherRepository.findByName(name)
    }
    override fun exists(id: Long): Boolean {
        return teacherRepository.existsById(id)
    }

    override fun exists(name: String): Boolean {
        return teacherRepository.findByName(name) != null
    }

    override fun findAll(): List<Teacher> {
        return teacherRepository.findAll()
    }

}


