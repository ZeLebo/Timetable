package nsu.service


import nsu.entities.university.Room

interface RoomService {

    fun addRoom(room: Room): Room
    fun updateRoom(room: Room): Room
    fun delete(id: Long)
    fun findByID(id: Long): Room?
    fun findByNumber(number: String): Room?
    fun exists(id: Long): Boolean
    fun exists(number: String): Boolean
    fun findAll(): List<Room>
}