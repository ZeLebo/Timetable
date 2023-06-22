package nsu.algos.repository

import nsu.algos.entities.TimetableOut
import org.springframework.data.jpa.repository.JpaRepository

interface AlgoRepository :JpaRepository<TimetableOut, Long>{

}