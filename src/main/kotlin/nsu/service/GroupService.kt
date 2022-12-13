package nsu.service

import nsu.entities.people.Group
import nsu.entities.people.Student

interface GroupService {
    fun addGroup(group: Group): Group
    fun updateGroup(group: Group): Group
    fun delete(id: Long)
    fun findByID(id: Long): Group?
    fun findByNumber(number: String): Group?
    fun exists(id: Long): Boolean
    fun exists(number: String): Boolean

    fun findAll(): List<Group>
    fun addStudent(groupId: Long, student: Student): Group
    fun updateGroup(groupId: Long, group: Group): Group
}