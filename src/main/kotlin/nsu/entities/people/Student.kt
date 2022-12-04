package nsu.entities.people

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * @property name - name of the student
 * @property last - surname of the student
 * */
@Entity
@Table(name = "students")
class Student(
    @Column(name = "name")
    var name: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "group_id",
        nullable = true
    )
    // need to connect students from students table to this group
    @field: JsonIgnore
    var group: Group? = null,

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val studentId: Long = 0,
)