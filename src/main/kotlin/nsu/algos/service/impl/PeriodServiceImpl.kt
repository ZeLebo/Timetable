package nsu.algos.service.impl

import nsu.algos.entities.Period
import nsu.algos.repository.PeriodRepository
import nsu.algos.service.PeriodService
import org.springframework.stereotype.Service

@Service
class PeriodServiceImpl (
    private val periodRepository: PeriodRepository
): PeriodService{
    override fun addPeriod(period: Period): Period {
        if (periodRepository.findByPeriodId(period.periodId) != null) {
            throw RuntimeException("Lesson already exists")
        }
        return periodRepository.save(period)
    }

    override fun updatePeriod(period: Period): Period {
        return periodRepository.save(period)
    }

    override fun updatePeriod(periodId: Long, period: Period): Period {
        this.findByID(periodId) ?: throw RuntimeException("Period not found")
        val periodDb = period
        return this.updatePeriod(periodDb)
    }

    override fun delete(id: Long) {
        this.findByID(id) ?: throw RuntimeException("Period not found")
        periodRepository.deleteById(id)

        if (this.findByID(id) != null) {
            throw RuntimeException("Period not deleted")
        }
    }

    override fun findByID(id: Long): Period? {
        return periodRepository.findByPeriodId(id)
    }

    override fun findByGroup(group: String): Period? {
        TODO("Not yet implemented")
    }

    override fun exists(id: Long): Boolean {
        return periodRepository.existsById(id)
    }

    override fun exists(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Period> {
        return periodRepository.findAll()
    }

}