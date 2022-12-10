package nsu.service

import nsu.entities.university.Lesson

interface RoomService {

    fun addRoom(on: Lesson): Lesson
    fun updateLesson(lesson: Lesson): Lesson
    fun delete(id: Long)
    fun findByID(id: Long): Lesson?
    fun findByName(name: String): Lesson?
    fun exists(id: Long): Boolean
    fun exists(name: String): Boolean
    fun findAll(): List<Lesson>
}