package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.domain.repository.HabitRepository

class SaveHabitUseCase(private val repository: HabitRepository) {

     suspend operator fun invoke(habit: Habit) {
         repository.save(habit)
     }
}
