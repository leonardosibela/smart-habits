package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.HabitCounter
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.repository.HabitCounterRepository

class PrePopulateDatabaseUseCase(private val habitCounterRepository: HabitCounterRepository) {

    suspend operator fun invoke() {
        habitCounterRepository.insert(HabitCounter(1, Periodicity.DAILY, 1))
        habitCounterRepository.insert(HabitCounter(2, Periodicity.WEEKLY, 1))
        habitCounterRepository.insert(HabitCounter(3, Periodicity.MONTHLY, 1))
        habitCounterRepository.insert(HabitCounter(4, Periodicity.YEARLY, 1))
    }
}