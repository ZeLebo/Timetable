package nsu.entities.university
/**
 * @property specializations - list of specializations on this faculty.
 * @property name - faculty name
 * */
data class Faculty(val specializations: ArrayList<Specialization>, val name: String)