package nsu.service

import nsu.entities.people.Group

interface GroupService {
    fun addGroup(group: Group): Group
    fun updateGroup(group: Group): Group
    fun delete(id: Long)
    fun findByID(id: Long): Group?
    fun findByNumber(number: String): Group?
    fun exists(id: Long): Boolean
    fun exists(number: String): Boolean

    fun findAll(): List<Group>
}