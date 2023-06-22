package nsu.algos.service

import nsu.algos.entities.TimetableOut

interface AlgoMaker {
    fun runAlgo():List<TimetableOut>
}