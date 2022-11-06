package nsu.entities.university

class Teacher(name: String, subjects: LinkedHashMap<Subject, String>) {
    private val name: String
        get() {
            return field
        }
    private val subjects: LinkedHashMap<Subject, String>
        get() {
            return field
        }

    init {
        this.subjects = subjects
        this.name = name
    }
}