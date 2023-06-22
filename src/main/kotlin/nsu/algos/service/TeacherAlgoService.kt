package nsu.algos.service

import nsu.algos.entities.TeacherAlgo


interface TeacherAlgoService {
    fun addTeacher(teacher: TeacherAlgo): TeacherAlgo
    fun updateTeacher(teacher: TeacherAlgo): TeacherAlgo
    fun delete(id: Long)
    fun findByID(id: Long): TeacherAlgo?
    fun findByName(name: String): TeacherAlgo?
    fun exists(id: Long): Boolean
    fun exists(name: String): Boolean
    fun findAll(): List<TeacherAlgo>
    fun updateTeacher(teacherId: Long, teacher: TeacherAlgo): TeacherAlgo
}