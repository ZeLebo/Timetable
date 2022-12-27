package nsu.service

import nsu.entities.timetable.TimetableContent
import nsu.entities.university.Lesson
import org.springframework.stereotype.Service

@Service
interface TimetableContentService {

    fun addTimetableContent(timetableContent: TimetableContent): TimetableContent
    fun findSpecialDiscipline(discipline: Lesson): MutableList<TimetableContent>
    fun findSpecialDay(day: String): MutableList<TimetableContent>
    fun findSpecialTeacher(teacher: String): MutableList<TimetableContent>
    fun findSpecialGroup(group: String): MutableList<TimetableContent>
    fun findSpecialRoom(room: String): MutableList<TimetableContent>
    fun findSpecialHour(hour: Int): MutableList<TimetableContent>
    fun findAll(): MutableList<TimetableContent>
    fun findById(id: Long): TimetableContent?
}