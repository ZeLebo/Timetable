package nsu.service

import nsu.entities.timetable.TimetableContent
import nsu.repository.TimetableContentRepository

interface TimetableContentService {

    fun addTimetableContent(timetableContent: TimetableContent): TimetableContent
    fun findSpecialDiscipline(discipline: String): MutableList<TimetableContent>
    fun findSpecialDay(day: String): MutableList<TimetableContent>
    fun findSpecialTeacher(teacher: String): MutableList<TimetableContent>
    fun findSpecialGroup(group: String): MutableList<TimetableContent>
    fun findSpecialRoom(room: String): MutableList<TimetableContent>
    fun findSpecialHour(hour: Int): MutableList<TimetableContent>
    fun findAll(): MutableList<TimetableContent>
}