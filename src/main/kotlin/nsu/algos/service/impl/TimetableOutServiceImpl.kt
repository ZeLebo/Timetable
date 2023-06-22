package nsu.algos.service.impl

import nsu.algos.entities.TimetableOut
import nsu.algos.repository.TimetableOutRepository
import nsu.algos.service.TimetableOutService
import org.springframework.stereotype.Service

@Service
class TimetableOutServiceImpl(
    private val timetableOutRepository: TimetableOutRepository
) : TimetableOutService {
    override fun findByGroup(group: String): List<TimetableOut> {
        val all = timetableOutRepository.findAll()
        return all.filter { it.courseName == group }
    }

    override fun findAll(): List<TimetableOut> {
        return timetableOutRepository.findAll()
    }

}