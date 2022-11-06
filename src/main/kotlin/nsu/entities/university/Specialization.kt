package nsu.entities.university

class Specialization(studyYears: ArrayList<StudyYear>, name: String) {
    private val studyYears: ArrayList<StudyYear>
        get() {
            return field
        }
    private val name: String
        get() {
            return field
        }

    init {
        this.name = name
        this.studyYears = studyYears
    }

}