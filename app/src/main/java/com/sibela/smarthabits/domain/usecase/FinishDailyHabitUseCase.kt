package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.repository.DailyHabitRepository

class FinishDailyHabitUseCase(private val dailyHabitRepository: DailyHabitRepository) {

    suspend operator fun invoke(dailyHabit: DailyHabit) {
        dailyHabitRepository.remove(dailyHabit)
    }
}