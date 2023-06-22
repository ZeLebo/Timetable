package nsu.algos.service

import nsu.algos.entities.TimetableOut

interface AlgoMakerService {
    fun runAlgo():List<TimetableOut>
}