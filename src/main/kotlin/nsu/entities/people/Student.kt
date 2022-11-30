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
    var id: Int = 0,
    @Column(name = "first_name")
    val first_name: String,
    @Column(name = "last_name")
    val last_name:String
)