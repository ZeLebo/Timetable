package nsu.repository

import java.sql.DriverManager
import nsu.entities.people.Student

//create class
class DBClient {

    //create function
    fun connect() {
        DriverManager.getConnection("jdbc:postgresql://localhost:5432/timetable_db", "admin", "admin")

    }

    //create table
    fun createTable() {
        //create connection
        val connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/timetable_db", "admin", "admin")
        //create statement
        val statement = connection.createStatement()
        //execute query
        statement.execute("CREATE TABLE students (id SERIAL PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255), group_id INT)")
    }


}
fun main() {

//    //create object
//    val dbClient = DBClient()
//    //call function
//    val connection = dbClient.connect()
//    println(connection.isValid(0))

    val dbName = "timetable_db"
    val jdbcUrl = "jdbc:postgresql://localhost:5432/${dbName}"
    val connection = DriverManager.getConnection(jdbcUrl, "admin", "admin")

    data class Students(val id: Int, val first_name: String, val last_name: String, val group_id: Int)

    val query = connection.prepareStatement("SELECT * from students")
    val result = query.executeQuery()
    val students = mutableListOf<Students>()

    while (result.next()) {
        val id = result.getInt("id")
        val firstName = result.getString("first_name")
        val lastName = result.getString("last_name")
        val groupId = result.getInt("group_id")
        students.add(Students(id, firstName, lastName, groupId))
    }


    data class Groups(val id: Int, val name: String)

    val groups = mutableListOf<Groups>()
    val queryForGroups = connection.prepareStatement("SELECT * from groups")
    val resultForGroups = queryForGroups.executeQuery()

    while (resultForGroups.next()) {
        val id = resultForGroups.getInt("id")
        val name = resultForGroups.getString("name")
        groups.add(Groups(id, name))
    }

    println(students)
    println(groups)

}