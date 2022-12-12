package nsu.service.impl

import nsu.entities.university.StudyYear
import nsu.repository.StudyYearRepository
import nsu.service.StudyYearService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class StudyYearImpl (
    private val studyYearRepository: StudyYearRepository
): StudyYearService {
    override fun addYear(studyYear: StudyYear): StudyYear {
        if (studyYearRepository.findByYear(studyYear.year) != null) {
            throw RuntimeException("Study year already exists")
        }
        return studyYearRepository.save(studyYear)
    }

    override fun updateYear(studyYear: StudyYear): StudyYear {
        return studyYearRepository.save(studyYear)
    }

    override fun delete(id: Long) {
        studyYearRepository.deleteById(id)
    }

    override fun findByID(id: Long): StudyYear? {
        return studyYearRepository.findById(id).orElse(null)
    }

    override fun findByYear(number: Int): StudyYear? {
        return studyYearRepository.findByYear(number)
    }
    override fun exists(id: Long): Boolean {
        return studyYearRepository.existsById(id)
    }

    override fun exists(number: Int): Boolean {
        return studyYearRepository.findByYear(number) != null
    }

    override fun findAll(): List<StudyYear> {
        return studyYearRepository.findAll()
    }
}