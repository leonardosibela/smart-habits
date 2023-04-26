package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.repository.ScheduleRepository
import java.time.LocalDateTime

class SetLastScheduleDateUseCase(private val scheduleRepository: ScheduleRepository) {

    suspend fun invoke(dateTime: LocalDateTime) {
        return scheduleRepository.setScheduleDate(dateTime)
    }
}
