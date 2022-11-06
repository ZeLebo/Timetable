package nsu.entities.university

class Faculty(specializations: ArrayList<Specialization>, name: String) {
    private val specializations: ArrayList<Specialization>
        get() {
            return field
        }
    private val name: String
        get() {
            return field
        }

    init {
        this.specializations = specializations
        this.name = name
    }
}