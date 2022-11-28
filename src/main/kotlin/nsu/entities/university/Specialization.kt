package nsu.entities.university
/**
 * Entity for specialization
 * @property studyYears - list of years (like courses) with lists of groups
 * @property name - name of the specialization
 * */
data class Specialization(val studyYears: ArrayList<StudyYear>, val name: String)