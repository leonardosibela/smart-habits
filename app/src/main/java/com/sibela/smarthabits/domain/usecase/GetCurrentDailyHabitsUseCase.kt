package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.resultBy
import com.sibela.smarthabits.domain.repository.DailyHabitRepository
import com.sibela.smarthabits.domain.repository.HabitCounterRepository

class GetCurrentDailyHabitsUseCase(
    private val dailyHabitRepository: DailyHabitRepository,
    private val habitCounterRepository: HabitCounterRepository
) {

    internal suspend operator fun invoke() = resultBy {
        val lastDailyCounter = habitCounterRepository.getLastDailyCounter()
        dailyHabitRepository.getHabitsForPeriod(lastDailyCounter.period)
    }
}