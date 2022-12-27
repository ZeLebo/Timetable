package nsu.service

import nsu.entities.timetable.TimetableContent

interface TimetableMaker {
    fun createTimeTable(): List<TimetableContent>
}