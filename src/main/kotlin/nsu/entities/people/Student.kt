package nsu.entities.people

import javax.persistence.*

/**
 * @property name - name of the student
 *
 * */
@Entity
@Table(name = "students")
class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "first_name")
    val first: String,
    @Column(name = "last_name")
    val last:String,
) {
    // empty constructor
    constructor() : this(0, "", "")
}