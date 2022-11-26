package nsu.services.impl

import nsu.entities.university.Lesson
import nsu.entities.university.Room
import nsu.entities.university.UniversityPlan

// this is service for creating timetable

class TimetableMaker(private val rooms: ArrayList<Room>, private val lessons: ArrayList<Lesson>, private val universityPlan: UniversityPlan, ) {

}