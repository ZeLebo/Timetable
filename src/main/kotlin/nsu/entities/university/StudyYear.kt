package nsu.entities.university

import nsu.entities.people.Group

/**
 * Study Year (course) entity
 * @property subjects - list of the subjects during this year
 * @property year - course of studying
 * @property groups - list of groups on this course in faculty
 * */
data class StudyYear (val subjects: ArrayList<Subject>, val year: Int, val groups: ArrayList<Group>)