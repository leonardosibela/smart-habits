package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.hikarisource.smarthabits.domain.model.YearlyHabit
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.domain.repository.HabitRepository
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository

class ResetYearlyHabitsUseCase(
    private val habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper,
    private val habitRepository: HabitRepository,
    private val yearlyHabitRepository: PeriodicHabitRepository<YearlyHabit>,
    private val habitCounterRepository: HabitCounterRepository,
) {

    suspend operator fun invoke() {
        val habits = habitRepository.getAllHabitsThatAreYearly().also {
            if (it.isEmpty()) return
        }

        val nextYearlyCounter = habitCounterRepository.getLastYearlyCounter().apply {
            id = 0
            period++
        }

        habitCounterRepository.insert(nextYearlyCounter)

        val yearlyHabits = habitToPeriodicityHabitMapper.toYearlyHabits(
            habits, false, nextYearlyCounter.period
        )

        yearlyHabits.forEach { yearlyHabit ->
            yearlyHabit.id = 0
            yearlyHabit.period = nextYearlyCounter.period
            yearlyHabitRepository.save(yearlyHabit)
        }
    }
}