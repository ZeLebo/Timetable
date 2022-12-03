package nsu.service.impl

import nsu.entities.people.Group
import nsu.repository.GroupRepository
import nsu.service.GroupService
import org.springframework.stereotype.Service

@Service
class GroupServiceImpl(
    private val groupRepository: GroupRepository
):  GroupService {
    override fun addGroup(group: Group): Group {
        if (groupRepository.existsByNumber(group.number)) {
            throw Exception("Group already exists")
        }
        return groupRepository.save(group)
    }

    override fun updateGroup(group: Group): Group {
        return groupRepository.save(group)
    }

    override fun delete(id: Long) {
        groupRepository.deleteById(id)
    }

    override fun findByID(id: Long): Group? {
        return groupRepository.findById(id).orElse(null)
    }

    override fun findByNumber(number: String): Group? {
        return groupRepository.findByNumber(number)
    }

    override fun exists(id: Long): Boolean {
        return groupRepository.existsById(id)
    }

    override fun exists(number: String): Boolean {
        return groupRepository.existsByNumber(number)
    }
}