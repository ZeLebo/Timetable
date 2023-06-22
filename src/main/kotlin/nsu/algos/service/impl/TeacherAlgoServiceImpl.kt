package nsu.algos.service.impl

import nsu.algos.entities.TeacherAlgo
import nsu.algos.repository.TeacherAlgoRepository
import nsu.algos.service.CourseService
import nsu.algos.service.TeacherAlgoService
import org.springframework.stereotype.Service


@Service
class TeacherAlgoServiceImpl(
    private val teacherRepository: TeacherAlgoRepository,
    private val courseService: CourseService,
): TeacherAlgoService {
    override fun addTeacher(teacher: TeacherAlgo): TeacherAlgo {
        if (teacherRepository.findByName(teacher.name) != null) {
            throw RuntimeException("Teacher already exists")
        }
        return teacherRepository.save(teacher)
    }
    override fun updateTeacher(teacher: TeacherAlgo): TeacherAlgo {
        return teacherRepository.save(teacher)
    }

    override fun updateTeacher(teacherId: Long, teacher: TeacherAlgo): TeacherAlgo {
        val teacherDb = this.findByID(teacherId)
            ?: throw RuntimeException("Teacher not found")
        teacherDb.name = teacher.name
        return this.updateTeacher(teacherDb)
    }

    override fun delete(id: Long) {
        this.findByID(id) ?: throw RuntimeException("Teacher not found")
        teacherRepository.deleteById(id)

        if (this.exists(id)) {
            throw RuntimeException("Teacher not deleted")
        }
    }


    override fun findByID(id: Long): TeacherAlgo? {
        return teacherRepository.findById(id).orElse(null)
    }

    override fun findByName(name: String): TeacherAlgo? {
        return teacherRepository.findByName(name)
    }
    override fun exists(id: Long): Boolean {
        return teacherRepository.existsById(id)
    }

    override fun exists(name: String): Boolean {
        return teacherRepository.findByName(name) != null
    }

    override fun findAll(): List<TeacherAlgo> {
        return teacherRepository.findAll()
    }

}
