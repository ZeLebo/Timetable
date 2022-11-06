package nsu.entities.people

import jdk.jfr.DataAmount

class Group(students: ArrayList<Student>, amount: Int, incomingYear: String){
    private val students: ArrayList<Student>
        get() {
            return field
        }
    private val amount: Int
        get(){
            return field
        }
    private val incomingYear: String
        get(){
            return field
        }

    init {
        this.amount = amount
        this.incomingYear = incomingYear
        this.students = students
    }

}