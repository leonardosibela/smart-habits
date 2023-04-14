package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.PeriodicHabit
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository

class FinishHabitUseCase<in T : PeriodicHabit>(
    private val periodicHabitRepository: PeriodicHabitRepository<T>,
) {

    suspend operator fun invoke(habit: T) {
        periodicHabitRepository.remove(habit)
    }
}