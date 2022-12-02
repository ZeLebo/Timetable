package nsu.controllers

import nsu.controllers.request.StudentRequest
import nsu.entities.people.Group
import nsu.entities.people.Student
import nsu.repository.GroupRepository
import nsu.repository.StudentRepository
import nsu.service.StudentService
import nsu.service.impl.StudentServiceImpl
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/add")
class AddController(
    private val studentService: StudentService,
    private val groupRepository: GroupRepository,
) {
    @RequestMapping("/")
    fun test(): String {
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @GetMapping("/student")
    fun showStudentForm(): String {
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @PostMapping("/student")
    fun addStudent(@RequestBody Student: StudentRequest): String {
        // check the existance of the user in database
        // if not exist, add it
        // if exists, return error
        if (studentService.exists(Student.first_name, Student.last_name)) {
            return "Sorry, but this student already exists"
        }

        // add student to the database
        studentService.addStudent(
            Student(
                Student.first_name, Student.last_name
            )
        )

        val tmp = studentService.findByFirstAndLast(Student.first_name, Student.last_name)
        if (tmp != null) {
            return "Student ${tmp.first} ${tmp.last} added successfully"
        }

        return "Unable to add student"
    }

    @PostMapping("/group")
    fun addGroup(): Group {
//        val group = Group(
//            0,
//            "test",
//            mutableListOf(
//                Student("test", "test"),
//                Student("test1", "test"),
//            ).map {
//                studentService.addStudent(it)
//            }.toMutableList()
//        )
        val group = groupRepository.findByNumber("test")!!
        var student = Student("zhora", "zhora")
        student = studentService.addStudent(student)
        // save data only after retrieving from db
        group.students.add(student)
        return groupRepository.save(group)
    }

    @GetMapping("/teacher")
    fun showTeacherForm(): String {
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @PostMapping("/teacher")
    fun addTeacher(@RequestBody Teacher: nsu.entities.people.Teacher): String {
        // here's going to be the logic to collect everything from the system, and show here beautiful page
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @GetMapping("/group")
    fun addGroup(@RequestBody Group: nsu.entities.people.Group): String {
        // here's going to be the logic to collect everything from the system, and show here beautiful page
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @GetMapping("/subject")
    fun addSubject(): String {
        // here's going to be the logic to collect everything from the system, and show here beautiful page
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @GetMapping("/lesson")
    fun addLesson(): String {
        // here's going to be the logic to collect everything from the system, and show here beautiful page
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @GetMapping("/room")
    fun addRoom(): String {
        // here's going to be the logic to collect everything from the system, and show here beautiful page
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }

    @GetMapping("/restriction")
    fun addRestriction(): String {
        // here's going to be the logic to collect everything from the system, and show here beautiful page
        return "Sorry, that page is not configured yet, but soon it will be showing the buttons and so on"
    }
}
