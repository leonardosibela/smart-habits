package com.sibela.smarthabits.domain.usecase

import androidx.annotation.VisibleForTesting
import com.sibela.smarthabits.data.entity.ScheduleDateEntity
import com.sibela.smarthabits.domain.repository.ScheduleRepository
import java.time.LocalDateTime

class GetLastScheduleDateUseCase(private val scheduleRepository: ScheduleRepository) {

    suspend fun invoke(): LocalDateTime? {
        return scheduleRepository.getLastScheduleDate()?.asLocalDateTime()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun ScheduleDateEntity?.asLocalDateTime(): LocalDateTime? {
        return this?.let {
            LocalDateTime.of(year, dayOfMonth, month, hour, minute, second, nanoSecond)
        }
    }
}