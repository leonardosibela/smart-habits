package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.repository.MonthlyHabitRepository

class FinishMonthlyHabitUseCase(private val monthlyHabitRepository: MonthlyHabitRepository) {

    suspend operator fun invoke(monthlyHabit: MonthlyHabit) {
        monthlyHabitRepository.remove(monthlyHabit)
    }
}