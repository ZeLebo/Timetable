package nsu.algos.service

import nsu.algos.entities.Period

interface AlgoMaker {
    fun runAlgo():List<Period>
}