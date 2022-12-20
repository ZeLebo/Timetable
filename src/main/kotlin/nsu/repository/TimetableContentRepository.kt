package nsu.repository

import nsu.entities.timetable.TimetableContent
import org.springframework.data.jpa.repository.JpaRepository

interface TimetableContentRepository: JpaRepository<TimetableContent, Long> {
    fun findByTimetableContentId(id: Long): TimetableContent?
    fun existsByTimetableContentId(id: Long): Boolean
    fun findByDiscipline(discipline: String): MutableList<TimetableContent>
    fun findByDay(day: String): MutableList<TimetableContent>
    fun findByTeacherName(teacher: String): MutableList<TimetableContent>
    fun findByGroupsNumber(group: String): MutableList<TimetableContent>
    fun findByRoomNumber(room: String): MutableList<TimetableContent>
    fun findByHour(hour: Int): MutableList<TimetableContent>

}