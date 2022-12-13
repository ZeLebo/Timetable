package nsu.service.impl

import nsu.entities.people.Group
import nsu.entities.people.Student
import nsu.repository.GroupRepository
import nsu.service.GroupService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GroupServiceImpl(
    private val groupRepository: GroupRepository,
    private val studentService: StudentService,
):  GroupService {
    @Transactional
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

    override fun updateGroup(groupId: Long, group: Group): Group {
        val groupDb = this.findByID(groupId)
            ?: throw RuntimeException("Group not found")
        groupDb.number = group.number
        groupDb.students = group.students
        return this.updateGroup(groupDb)
    }

    @Transactional
    override fun delete(id: Long) {
        val group = groupRepository.findById(id).orElse(null) ?: throw RuntimeException("Group not found")
        // delete all the students in the group
        group.students.forEach {
            studentService.delete(it.studentId)
        }
        groupRepository.deleteById(id)

        if (groupRepository.existsById(id)) {
            throw RuntimeException("Group not found")
        }
    }

    override fun addStudent(groupId: Long, student: Student): Group {
        val groupDb = this.findByID(groupId)
            ?: throw RuntimeException("Group not found")
        val studentDb = studentService.addStudent(student)
        groupDb.students.add(studentDb)
        return this.updateGroup(groupDb)
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

    override fun findAll(): List<Group> {
        return groupRepository.findAll()
    }
}