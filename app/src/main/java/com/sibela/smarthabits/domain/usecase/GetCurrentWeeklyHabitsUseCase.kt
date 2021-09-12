package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.resultBy
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.WeeklyHabitRepository

class GetCurrentWeeklyHabitsUseCase(
    private val weeklyHabitRepository: WeeklyHabitRepository,
    private val habitCounterRepository: HabitCounterRepository
) {

    internal suspend operator fun invoke() = resultBy {
        val lastWeeklyCounter = habitCounterRepository.getLastWeeklyCounter()
        weeklyHabitRepository.getHabitsForPeriod(lastWeeklyCounter.period)
    }
}