package nsu.repository

import nsu.entities.timetable.TimetableContent
import org.springframework.data.jpa.repository.JpaRepository

interface TimetableContentRepository: JpaRepository<TimetableContent, Long> {
    fun findByDay(day: String): TimetableContent
}