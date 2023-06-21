package nsu.algos.repository

import nsu.algos.entities.TeacherAlgo
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherAlgoRepository : JpaRepository<TeacherAlgo, Long> {
    fun findByTeacherId(id: Long): TeacherAlgo?
    fun findByName(name: String): TeacherAlgo?
}