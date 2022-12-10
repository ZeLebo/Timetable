package nsu.service.impl

import nsu.repository.TeacherRepository
import org.springframework.stereotype.Service
import nsu.entities.people.Teacher
import nsu.service.TeacherService
import java.lang.RuntimeException

@Service
class TeacherServiceImpl(
    private val teacherRepository: TeacherRepository
):  TeacherService{
    override fun addTeacher(teacher: Teacher): Teacher {
        if (teacherRepository.findByName(teacher.name) != null) {
            throw RuntimeException("Teacher already exists")
        }
        return teacherRepository.save(teacher)
    }

    override fun updateTeacher(teacher: Teacher): Teacher {
        return teacherRepository.save(teacher)
    }

    override fun delete(id: Long) {
        teacherRepository.deleteById(id)
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

