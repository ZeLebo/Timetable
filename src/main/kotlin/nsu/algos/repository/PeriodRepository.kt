package nsu.algos.repository

import nsu.algos.entities.Period
import org.springframework.data.jpa.repository.JpaRepository

interface PeriodRepository : JpaRepository<Period, Long> {
    fun findByPeriodId(periodId: Long): Period
}