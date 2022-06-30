package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.domain.repository.YearlyHabitRepository

class ResetYearlyHabitsUseCase(
    private val habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper,
    private val habitRepository: HabitRepository,
    private val yearlyHabitRepository: YearlyHabitRepository,
    private val habitCounterRepository: HabitCounterRepository
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