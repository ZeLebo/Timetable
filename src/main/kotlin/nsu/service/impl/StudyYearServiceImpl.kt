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
    private val subjectServiceImpl: SubjectServiceImpl,
    private val groupService: GroupServiceImpl
): StudyYearService {
    override fun addStudyYear(studyYear: StudyYear): StudyYear {
//        if (studyYearRepository.findByYear(studyYear.year) != null) {
//            throw RuntimeException("Study year already exists")
//        }

        var studyYearDb = studyYearRepository.save(studyYear)

        studyYearDb.subjects = studyYear.subjects.map {
            subjectServiceImpl.addSubject(
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