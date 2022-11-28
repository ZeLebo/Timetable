package nsu.repository

import java.sql.DriverManager
import nsu.entities.people.Student
import java.sql.Connection

//create class
class DBClient {

    //create function
    private fun connect(): Connection {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/timetable_db", "admin", "admin")
    }

    //create table students
    fun createTableStudents() {
        //create connection
        val connection = connect()
        //create statement
        val statement = connection.createStatement()
        //execute query
        try {
            statement.execute("CREATE TABLE students (id SERIAL PRIMARY KEY, first_name VARCHAR(255), second_name VARCHAR(255), group_id INT)")
        } catch (e: Exception) {
            println("Table students already exists")
        }
    }

    //create table groups
    fun createTableGroups() {
        //create connection
        val connection = connect()
        //create statement
        val statement = connection.createStatement()
        //execute query
        try {
            statement.execute("CREATE TABLE groups (id SERIAL PRIMARY KEY, name VARCHAR(255))")
        } catch (e: Exception) {
            println("Table group already exists")
        }
    }

    //add record to table groups
    fun addGroup(name: String) {
        //create connection
        val connection = connect()
        //create statement
        val statement = connection.createStatement()
        //execute query
        statement.execute("INSERT INTO groups (name) VALUES ('$name')")
    }

    //add record to table students
    fun addStudent(student: Student) {
        //create connection
        val connection = connect()
        //create statement
        val statement = connection.createStatement()
        //execute query
        statement.execute("INSERT INTO students (first_name, last_name, group_id) VALUES ('${student.first_name}', '${student.second_name}', ${student.group_id})")
    }

    //get all records from table
    fun getAllStudents(): List<Student> {
        //create connection
        val connection = connect()
        //create statement
        val statement = connection.createStatement()
        //execute query
        val resultSet = statement.executeQuery("SELECT * FROM students")
        //create list of students
        val students = mutableListOf<Student>()
        //iterate over result set
        while (resultSet.next()) {
            //create student
            val student = Student(resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getInt("id"), resultSet.getInt("group_id"))
            //add student to list
            students.add(student)
        }
        //return list of students
        return students
    }
}

