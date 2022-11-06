package nsu.entities.people

import nsu.entities.university.Subject

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