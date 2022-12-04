package nsu.service.impl

import nsu.entities.people.Student
import nsu.repository.StudentRepository
import nsu.service.StudentService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository
):  StudentService {
    override fun addStudent(student: Student): Student {
        if (studentRepository.findByName(student.name) != null) {
            throw RuntimeException("Student already exists")
        }
        return studentRepository.save(student)
    }

    override fun updateStudent(student: Student): Student {
        return studentRepository.save(student)
    }

    override fun delete(id: Long) {
        studentRepository.deleteById(id)
    }

    override fun findByID(id: Long): Student? {
        return studentRepository.findById(id).orElse(null)
    }

    override fun findByName(name: String): Student? {
        return studentRepository.findByName(name)
    }
    override fun exists(id: Long): Boolean {
        return studentRepository.existsById(id)
    }

    override fun exists(name: String): Boolean {
        return studentRepository.findByName(name) != null
    }

    override fun findAll(): List<Student> {
        return studentRepository.findAll()
    }
}