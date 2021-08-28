package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.repository.HabitRepository

class DeleteHabitUseCase(private val habitRepository: HabitRepository) {

    internal suspend operator fun invoke(habit: Habit) {
        habitRepository.delete(habit)
    }
}