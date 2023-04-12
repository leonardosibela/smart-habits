package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.common.resultBy
import com.sibela.smarthabits.domain.model.PeriodicHabit
import com.sibela.smarthabits.domain.repository.PeriodicHabitRepository

class GetCurrentHabitsUseCase<T : PeriodicHabit>(
    private val periodicHabitRepository: PeriodicHabitRepository<T>,
) {

    suspend operator fun invoke(): Result<List<T>> = resultBy {
        periodicHabitRepository.getHabitsForLastPeriod()
    }
}