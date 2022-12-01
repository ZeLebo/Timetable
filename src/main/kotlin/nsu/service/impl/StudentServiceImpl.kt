package nsu.service.impl

import nsu.entities.people.Student
import nsu.repository.StudentRepository
import nsu.service.StudentService
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository
):  StudentService {
    override fun addStudent(student: Student): Student {
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

    override fun findByFirstAndLast(first: String, last: String): Student? {
        return studentRepository.findByFirstAndLast(first, last)
    }

    override fun exists(id: Long): Boolean {
        return studentRepository.existsById(id)
    }

    override fun exists(first: String, last: String): Boolean {
        return studentRepository.existsByFirstAndLast(first, last)
    }
}