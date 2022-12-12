package nsu.service.impl

import nsu.entities.people.Group
import nsu.entities.university.StudyYear
import nsu.entities.university.Subject
import nsu.repository.StudyYearRepository
import nsu.service.StudyYearService
import org.springframework.stereotype.Service

@Service
class StudyYearServiceImpl(
    private val studyYearRepository: StudyYearRepository,
    private val subjectService: SubjectServiceImpl,
    private val groupService: GroupServiceImpl
): StudyYearService {
    override fun addStudyYear(studyYear: StudyYear): StudyYear {
        // check if exists
        if (studyYear.specializationName?.let {
                studyYearRepository.findByYearAndSpecializationName(studyYear.year,
                    "No specialization"
                )
            } != null) {
            throw RuntimeException("Study year already exist")
        }

        var studyYearDb = studyYearRepository.save(studyYear)

        studyYearDb.subjects = studyYear.subjects.map {
            subjectService.addSubject(
                Subject(
                    it.name,
                    studyYearDb
                )
            )
        }.toMutableList()

        studyYearDb = studyYearRepository.save(studyYear)

        studyYearDb.groups = studyYear.groups.map {
            groupService.addGroup(
                Group(
                    it.number,
                    it.students,
                )
            )
        }.toMutableList()

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

    override fun findByYearAndSpecializationName(year: Int, name: String): StudyYear? {
        return studyYearRepository.findByYearAndSpecializationName(year, name)
    }
    override fun exists(id: Long): Boolean {
        return studyYearRepository.existsById(id)
    }

    override fun exists(year: Int, name: String): Boolean {
        return studyYearRepository.findByYearAndSpecializationName(year, name) != null
    }

    override fun findAll(): List<StudyYear> {
        return studyYearRepository.findAll()
    }
}