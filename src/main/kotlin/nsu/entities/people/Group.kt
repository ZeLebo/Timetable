package nsu.entities.people
<<<<<<< HEAD

data class Group( val name: String, val id: Int)
=======
/**
 * Entity for group
 * @property students - list of students in this group
 * @property amount - amount of students in this group
 * @property incomingYear - year of incoming to the university
 * */
data class Group(val students: ArrayList<Student>, val amount: Int, val incomingYear: String)
>>>>>>> a227c64 (Kdocs)
