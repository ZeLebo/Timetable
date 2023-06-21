package nsu.algos.service

import nsu.algos.entities.RoomAlgo


interface RoomAlgoService {
    fun addRoom(room: RoomAlgo): RoomAlgo
    fun updateRoom(room: RoomAlgo): RoomAlgo
    fun delete(id: Long)
    fun findByID(id: Long): RoomAlgo?
    fun findByNumber(number: String): RoomAlgo?
    fun exists(id: Long): Boolean
    fun exists(number: String): Boolean
    fun findAll(): List<RoomAlgo>
    fun updateRoom(id: Long, room: RoomAlgo): RoomAlgo
}