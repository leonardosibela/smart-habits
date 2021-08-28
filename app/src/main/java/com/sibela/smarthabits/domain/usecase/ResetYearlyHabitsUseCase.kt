package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitMapper
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.domain.repository.YearlyHabitRepository

class ResetYearlyHabitsUseCase(
    private val habitMapper: HabitMapper,
    private val habitRepository: HabitRepository,
    private val yearlyHabitRepository: YearlyHabitRepository,
    private val habitCounterRepository: HabitCounterRepository
) {

    internal suspend operator fun invoke() {
        val yearlyCounter = habitCounterRepository.getLastYearlyCounter()
        yearlyCounter.period = yearlyCounter.period++
        habitCounterRepository.update(yearlyCounter)
        val habits = habitRepository.getAllHabitsThatAreYearly()
        val yearlyHabits = habitMapper.toYearlyHabits(habits, false, yearlyCounter.period)
        yearlyHabits.forEach { yearlyHabit -> yearlyHabitRepository.save(yearlyHabit) }
    }
}