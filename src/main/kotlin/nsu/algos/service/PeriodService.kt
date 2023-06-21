package nsu.algos.service

import nsu.algos.entities.Period

interface PeriodService {
    fun addPeriod(period: Period): Period
    fun updatePeriod(period: Period): Period
    fun delete(id: Long)
    fun findByID(id: Long): Period?
    fun findByGroup(group: String): Period?
    fun exists(id: Long): Boolean
    fun exists(name: String): Boolean
    fun findAll(): List<Period>
    fun updatePeriod(periodId: Long, course: Period): Period
}