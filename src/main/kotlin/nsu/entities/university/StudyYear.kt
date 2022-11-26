package nsu.entities.university
/**
 * Study Year (course) entity
 * @property subjects - list of the subjects during this year
 * @property year - year of incoming???
 * */
data class StudyYear (val subjects: ArrayList<Subject>, val year: Int)