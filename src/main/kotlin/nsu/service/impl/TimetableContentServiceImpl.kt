package nsu.service.impl

import nsu.entities.timetable.TimetableContent
import nsu.entities.university.Lesson
import nsu.repository.TimetableContentRepository
import nsu.service.TimetableContentService

class TimetableContentServiceImpl(
    private val timetableContentRepository: TimetableContentRepository
): TimetableContentService {

    override fun findById(id: Long): TimetableContent? {
        return timetableContentRepository.findByTimetableContentId(id)
    }

    override fun addTimetableContent(timetableContent: TimetableContent): TimetableContent {
        return timetableContentRepository.save(timetableContent)
    }

    override fun findSpecialDiscipline(discipline: Lesson): MutableList<TimetableContent> {
        val all = timetableContentRepository.findAll()
        // filter where the discipline is that
        return all.filter { it.discipline == discipline }.toMutableList()
    }

    override fun findSpecialDay(day: String): MutableList<TimetableContent> {
        val all = timetableContentRepository.findAll()
        // filter where the day is that
        return all.filter { it.day == day }.toMutableList()
    }

    override fun findSpecialTeacher(teacher: String): MutableList<TimetableContent> {
        val all = timetableContentRepository.findAll()
        // filter where the teacher is that
        return all.filter { it.teacher?.name == teacher }.toMutableList()
    }

    override fun findSpecialGroup(group: String): MutableList<TimetableContent> {
        val all = timetableContentRepository.findAll()
        // filter where the group is that
        return all.filter { it.groups.any { it.number == group } }.toMutableList()
    }

    override fun findSpecialRoom(room: String): MutableList<TimetableContent> {
        val all = timetableContentRepository.findAll()
        // filter where the room is that
        return all.filter { it.room?.number == room }.toMutableList()
    }

    override fun findSpecialHour(hour: Int): MutableList<TimetableContent> {
        val all = timetableContentRepository.findAll()
        // filter where the hour is that
        return all.filter { it.hour == hour }.toMutableList()
    }

    override fun findAll(): MutableList<TimetableContent> {
        return timetableContentRepository.findAll()
    }

}