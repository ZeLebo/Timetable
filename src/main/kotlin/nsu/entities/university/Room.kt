package nsu.entities.university

/**
 * Room entity
 * @property capacity - capacity of the room
 * @property roomType - type of the room (for lections, for seminars or for labs)
 * */
data class Room(val capacity: Int, val roomType: String)