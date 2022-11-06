package nsu.entities.university

class Lesson(name: String, roomType: String, subjectType: String) {
    private val name: String
        get() {
            return field
        }
    private val roomType: String
        get(){
            return field
        }
    private val subjectType: String
        get(){
            return field
        }
    init {
        this.name = name
        this.roomType = roomType
        this.subjectType = subjectType
    }
}