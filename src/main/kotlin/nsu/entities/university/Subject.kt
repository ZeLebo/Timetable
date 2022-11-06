package nsu.entities.university

class Subject(lessons: ArrayList<Lesson>, name: String) {
    private val lessons: ArrayList<Lesson>
        get() {
            return field
        }
    private val name: String
        get() {
            return field
        }

    init {
        this.name = name
        this.lessons = lessons
    }

}