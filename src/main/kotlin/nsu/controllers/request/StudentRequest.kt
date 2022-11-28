package nsu.controllers.request

// class to sereliase the `first_name`, `second_name`, `id` and `group_id` from request body
class StudentRequest(val first_name: String, val last_name: String, val id: Int, val group_id: Int)