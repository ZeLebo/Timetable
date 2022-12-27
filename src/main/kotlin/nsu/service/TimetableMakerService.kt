package nsu.service

import nsu.entities.timetable.TimetableContent

interface TimetableMakerService {
    fun createTimeTable(): MutableList<TimetableContent>
}