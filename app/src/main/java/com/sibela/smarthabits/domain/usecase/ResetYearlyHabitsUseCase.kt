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
        val yearlyCounter = habitCounterRepository.getLastYearlyCounter()
        yearlyCounter.id = 0
        yearlyCounter.period = yearlyCounter.period++
        habitCounterRepository.insert(yearlyCounter)
        val habits = habitRepository.getAllHabitsThatAreYearly()
        val yearlyHabits = habitToPeriodicityHabitMapper.toYearlyHabits(habits, false, yearlyCounter.period)
        yearlyHabits.forEach { yearlyHabit ->
            yearlyHabit.id = 0
            yearlyHabit.period = yearlyCounter.period
            yearlyHabitRepository.save(yearlyHabit)
        }
    }
}