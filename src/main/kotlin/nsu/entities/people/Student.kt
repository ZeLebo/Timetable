package nsu.entities.people

import javax.persistence.*

/**
 * @property first - name of the student
 * @property last - surname of the student
 * */
@Entity
@Table(name = "students")
class Student(
    @Column(name = "first_name")
    val first: String,
    @Column(name = "last_name")
    val last: String,

//    @ManyToOne
//    @JoinTable()
    @Column(name = "group_number")
    val groupNumber: String,

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val studentId: Long = 0,
) {
    // empty constructor
    constructor(first: String, last: String, groupNumber: String) : this(first, last, groupNumber, 0)
}