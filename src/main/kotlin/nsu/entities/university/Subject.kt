package nsu.entities.university
/**
 * Subject entity
 * @property lessons - list of lessons for this subject (like amount of study hours for this subject)
 * @property name - name of the subject
 * */
data class Subject(val lessons: ArrayList<Lesson>, val name: String)