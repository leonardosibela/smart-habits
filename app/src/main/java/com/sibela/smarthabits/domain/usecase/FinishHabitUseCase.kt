package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.PeriodicHabit
import com.sibela.smarthabits.domain.repository.PeriodicHabitRepository

class FinishHabitUseCase<in T : PeriodicHabit>(
    private val periodicHabitRepository: PeriodicHabitRepository<T>,
) {

    suspend operator fun invoke(habit: T) {
        periodicHabitRepository.remove(habit)
    }
}