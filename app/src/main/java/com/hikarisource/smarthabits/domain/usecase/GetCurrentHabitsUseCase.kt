package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.common.Result
import com.hikarisource.smarthabits.domain.common.resultBy
import com.hikarisource.smarthabits.domain.model.PeriodicHabit
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository

class GetCurrentHabitsUseCase<T : PeriodicHabit>(
    private val periodicHabitRepository: PeriodicHabitRepository<T>,
) {

    suspend operator fun invoke(): Result<List<T>> = resultBy {
        periodicHabitRepository.getHabitsForLastPeriod()
    }
}
