package nsu.algos.service.impl

import nsu.algos.entities.Period
import nsu.algos.repository.PeriodRepository
import nsu.algos.service.PeriodService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class PeriodServiceImpl (
    private val periodRepository: PeriodRepository
): PeriodService{
    override fun addPeriod(period: Period): Period {
        if (periodRepository.findByGroupId(period.groupId) != null) {
            throw RuntimeException("Lesson already exists")
        }
        return periodRepository.save(period)
    }

    override fun updatePeriod(period: Period): Period {
        return periodRepository.save(period)
    }

    override fun updatePeriod(periodId: Long, course: Period): Period {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }

    override fun findByID(id: Long): Period? {
        TODO("Not yet implemented")
    }

    override fun findByGroup(group: String): Period? {
        TODO("Not yet implemented")
    }

    override fun exists(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun exists(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Period> {
        TODO("Not yet implemented")
    }

}