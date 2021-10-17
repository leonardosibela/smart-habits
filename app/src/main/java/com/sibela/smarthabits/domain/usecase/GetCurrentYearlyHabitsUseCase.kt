package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.resultBy
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.YearlyHabitRepository

class GetCurrentYearlyHabitsUseCase(
    private val yearlyHabitRepository: YearlyHabitRepository,
    private val habitCounterRepository: HabitCounterRepository
) {

    suspend operator fun invoke() = resultBy {
        val lastYearlyCounter = habitCounterRepository.getLastYearlyCounter()
        yearlyHabitRepository.getHabitsForPeriod(lastYearlyCounter.period)
    }
}