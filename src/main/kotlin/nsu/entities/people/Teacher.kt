package nsu.entities.people

import nsu.entities.university.Subject

data class Teacher(val name: String, val subjects: LinkedHashMap<Subject, String>)