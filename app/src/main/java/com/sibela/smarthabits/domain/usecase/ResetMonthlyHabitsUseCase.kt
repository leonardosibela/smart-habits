package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitMapper
import com.sibela.smarthabits.domain.repository.MonthlyHabitRepository
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository

class ResetMonthlyHabitsUseCase(
    private val habitMapper: HabitMapper,
    private val habitRepository: HabitRepository,
    private val monthlyHabitRepository: MonthlyHabitRepository,
    private val habitCounterRepository: HabitCounterRepository
) {

    internal suspend operator fun invoke() {
        val monthlyCounter = habitCounterRepository.getLastMonthlyCounter()
        monthlyCounter.period = monthlyCounter.period++
        habitCounterRepository.update(monthlyCounter)
        val habits = habitRepository.getAllHabitsThatAreMonthly()
        val monthlyHabits = habitMapper.toMonthlyHabits(habits, false, monthlyCounter.period)
        monthlyHabits.forEach { monthlyHabit -> monthlyHabitRepository.save(monthlyHabit) }
    }
}