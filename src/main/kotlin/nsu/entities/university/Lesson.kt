package nsu.entities.university
/**
 * Lesson entity (lesson is like  para)
 * @property name - name of the lesson
 * @property roomType - type of the room, which is needed for this lesson
 * @property subjectType - type of the subject (Lection, seminar, lab)
 * */
data class Lesson(val name: String, val roomType: String, val subjectType: String)