package nsu.algos.service

import nsu.algos.entities.GroupAlgo


interface GroupAlgoService {
    fun addGroup(group: GroupAlgo): GroupAlgo
    fun updateGroup(group: GroupAlgo): GroupAlgo
    fun delete(id: Long)
    fun findByID(id: Long): GroupAlgo?
    fun findByNumber(number: String): GroupAlgo?
    fun exists(id: Long): Boolean
    fun exists(number: String): Boolean
    fun findAll(): List<GroupAlgo>
    fun updateGroup(groupId: Long, group: GroupAlgo): GroupAlgo
}