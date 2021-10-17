package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.repository.WeeklyHabitRepository

class FinishWeeklyHabitUseCase(private val weeklyHabitRepository: WeeklyHabitRepository) {

    suspend operator fun invoke(weeklyHabit: WeeklyHabit) {
        weeklyHabitRepository.remove(weeklyHabit)
    }
}