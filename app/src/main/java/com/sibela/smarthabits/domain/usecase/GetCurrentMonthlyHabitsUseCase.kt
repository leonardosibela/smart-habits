package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.resultBy
import com.sibela.smarthabits.domain.repository.MonthlyHabitRepository
import com.sibela.smarthabits.domain.repository.HabitCounterRepository

class GetCurrentMonthlyHabitsUseCase(
    private val monthlyHabitRepository: MonthlyHabitRepository,
    private val habitCounterRepository: HabitCounterRepository
) {

    internal suspend operator fun invoke() = resultBy {
        val lastMonthlyCounter = habitCounterRepository.getLastMonthlyCounter()
        monthlyHabitRepository.getHabitsForPeriod(lastMonthlyCounter.period)
    }
}