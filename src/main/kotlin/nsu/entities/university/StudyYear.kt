package nsu.entities.university

class StudyYear (subjects: ArrayList<Subject>, year: Int){
    private val subjects: ArrayList<Subject>
        get() {
            return field
        }
    private val year: Int
        get(){
            return field
        }
    init {
        this.subjects = subjects
        this.year = year
    }

}