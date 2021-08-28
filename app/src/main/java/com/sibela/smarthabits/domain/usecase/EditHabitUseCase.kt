package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.repository.HabitRepository

class EditHabitUseCase(private val habitRepository: HabitRepository) {

    internal suspend operator fun invoke(habit: Habit) {
        habitRepository.edit(habit)
    }
}