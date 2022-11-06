package nsu.entities.university

class UniversityPlan(faculties: ArrayList<Faculty>) {
    private val faculties: ArrayList<Faculty>
        get() {
            return field
        }

    init {
        this.faculties = faculties
    }
}