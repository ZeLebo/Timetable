package nsu.algos.service

import nsu.algos.entities.TimetableOut

interface TimetableOutService {
    fun findByGroup(group: String):List<TimetableOut>
    fun findAll():List<TimetableOut>
}