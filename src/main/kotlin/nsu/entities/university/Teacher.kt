package nsu.entities.university

class Teacher(name: String, subjects: ArrayList<Subject>, subjectTypes: ArrayList<String>) {
    private val name: String
        get() {
            return field
        }
    private val subjects: ArrayList<Subject>
        get() {
            return field
        }
    private val subjectTypes: ArrayList<String>
        get() {
            return field
        }

    init {
        this.subjects = subjects
        this.name = name
        this.subjectTypes = subjectTypes
    }

}