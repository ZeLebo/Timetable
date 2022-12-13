package nsu.service.impl

import nsu.entities.university.Subject
import nsu.entities.university.Lesson
import nsu.repository.SubjectRepository
import nsu.service.SubjectService
import org.springframework.stereotype.Service

@Service
class SubjectServiceImpl(
    private val subjectRepository: SubjectRepository,
    private val lessonService: LessonServiceImpl,
):  SubjectService {
    override fun addSubject(subject: Subject): Subject{
        if (subjectRepository.findByName(subject.name)!= null) {
            throw Exception("Subject already exists")
        }

        val subjectDb = subjectRepository.save(subject)


        subjectDb.lessons = subject.lessons.map {
            lessonService.addLesson(
                Lesson(
                    it.name,
                    it.roomType,
                    it.subjectType,
                    subjectDb
                )
            )
        }.toMutableList()
        // update the group and return
        return subjectRepository.save(subject)
    }

    override fun updateSubject(subject: Subject): Subject {
        return subjectRepository.save(subject)
    }

    override fun updateSubject(subjectId: Long, subject: Subject): Subject {
        val subjectDb = this.findByID(subjectId)
            ?: throw RuntimeException("Subject not found")
        subjectDb.name = subject.name
        subjectDb.lessons = subject.lessons
        return this.updateSubject(subjectDb)
    }

    override fun addLesson(subjectId: Long, lesson: Lesson): Lesson {
        val subjectDb = this.findByID(subjectId)
            ?: throw RuntimeException("Subject not found")
        lesson.subject = subjectDb
        val lessonDb = lessonService.addLesson(lesson)
        subjectDb.lessons.add(lessonDb)
        this.updateSubject(subjectDb)
        return lessonDb
    }

    override fun delete(id: Long) {
        // delete all the students in the group
        subjectRepository.findById(id).orElse(null)?.lessons?.forEach {
            lessonService.delete(it.lessonId)
        }
        subjectRepository.deleteById(id)
    }

    override fun findByID(id: Long): Subject? {
        return subjectRepository.findBySubjectId(id) ?: throw RuntimeException("Subject not found")
    }

    override fun findByName(name: String): Subject? {
        return subjectRepository.findByName(name)
    }

    override fun exists(id: Long): Boolean {
        return subjectRepository.existsById(id)
    }

    override fun findAll(): List<Subject> {
        return subjectRepository.findAll()
    }
}