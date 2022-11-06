package nsu.entities.university

class Room(capacity: Int, roomType: String) {
    private val capacity: Int
        get() {
            return field
        }
    private val roomType: String
        get() {
            return field
        }
    init{
        this.roomType = roomType
        this.capacity = capacity
    }

}