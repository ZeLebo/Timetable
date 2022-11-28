package nsu.entities.timetable

import nsu.entities.people.Group
import nsu.entities.people.Teacher
import nsu.entities.university.Room

/**
 * Lesson in timetable entity.
 *
 * @property discipline - name of discipline
 * @property day - weekday of the lesson
 * @property hour - time of the lesson
 * @property teacher - name of the teacher
 * @property group - group which will attend the lesson
 * @property room - room where the lesson will be
 * */
data class TimetableContent (val discipline: String, val day: String, val hour: Int, val teacher: Teacher, val group: ArrayList<Group>, val room: Room)