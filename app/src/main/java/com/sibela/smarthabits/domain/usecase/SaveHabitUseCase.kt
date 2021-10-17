package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.repository.HabitRepository

class SaveHabitUseCase(private val repository: HabitRepository) {

     suspend operator fun invoke(habit: Habit) {
         repository.save(habit)
     }
}