package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.repository.YearlyHabitRepository

class FinishYearlyHabitUseCase(private val yearlyHabitRepository: YearlyHabitRepository) {

    suspend operator fun invoke(yearlyHabit: YearlyHabit) {
        yearlyHabitRepository.remove(yearlyHabit)
    }
}