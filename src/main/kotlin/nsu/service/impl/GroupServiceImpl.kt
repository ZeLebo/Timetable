package nsu.service.impl

import nsu.entities.people.Group
import nsu.entities.people.Student
import nsu.repository.GroupRepository
import nsu.service.GroupService
import org.springframework.stereotype.Service

@Service
class GroupServiceImpl(
    private val groupRepository: GroupRepository,
    private val studentService: StudentServiceImpl,
):  GroupService {
    override fun addGroup(group: Group): Group {
        if (groupRepository.existsByNumber(group.number)) {
            throw Exception("Group already exists")
        }
        // get the saved group from database
        val groupDb = groupRepository.save(group)
        // adding all the students to the group
        groupDb.students = group.students.map {
            studentService.addStudent(
                Student(
                    it.name,
                    groupDb
                )
            )
        }.toMutableList()
        // update the group and return
        return groupRepository.save(group)
    }

    override fun updateGroup(group: Group): Group {
        return groupRepository.save(group)
    }

    override fun delete(id: Long) {
        // delete all the students in the group
        groupRepository.findById(id).orElse(null)?.students?.forEach {
            studentService.delete(it.studentId)
        }
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