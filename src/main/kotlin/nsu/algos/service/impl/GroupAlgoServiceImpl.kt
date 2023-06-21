package nsu.algos.service.impl

import nsu.algos.entities.GroupAlgo
import nsu.algos.repository.GroupAlgoRepository
import nsu.algos.service.GroupAlgoService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GroupAlgoServiceImpl(
    private val groupRepository: GroupAlgoRepository
): GroupAlgoService {
    @Transactional
    override fun addGroup(group: GroupAlgo): GroupAlgo {
        if (groupRepository.existsByNumber(group.name)) {
            throw Exception("Group already exists")
        }
        return groupRepository.save(group)
    }

    override fun updateGroup(group: GroupAlgo): GroupAlgo {
        return groupRepository.save(group)
    }

    override fun updateGroup(groupId: Long, group: GroupAlgo): GroupAlgo {
        val groupDb = this.findByID(groupId)
            ?: throw RuntimeException("Group not found")
        groupDb.name = group.name
        return this.updateGroup(groupDb)
    }

    @Transactional
    override fun delete(id: Long) {
        groupRepository.deleteById(id)

        if (groupRepository.existsById(id)) {
            throw RuntimeException("Group not found")
        }
    }

    override fun findByID(id: Long): GroupAlgo? {
        return groupRepository.findById(id).orElse(null)
    }

    override fun findByNumber(number: String): GroupAlgo? {
        return groupRepository.findByNumber(number)
    }

    override fun exists(id: Long): Boolean {
        return groupRepository.existsById(id)
    }

    override fun exists(number: String): Boolean {
        return groupRepository.existsByNumber(number)
    }

    override fun findAll(): List<GroupAlgo> {
        return groupRepository.findAll()
    }
}