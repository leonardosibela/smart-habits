package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.domain.repository.MonthlyHabitRepository

class ResetMonthlyHabitsUseCase(
    private val habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper,
    private val habitRepository: HabitRepository,
    private val monthlyHabitRepository: MonthlyHabitRepository,
    private val habitCounterRepository: HabitCounterRepository
) {

    internal suspend operator fun invoke() {
        val monthlyCounter = habitCounterRepository.getLastMonthlyCounter()
        monthlyCounter.id = 0
        monthlyCounter.period = monthlyCounter.period++
        habitCounterRepository.insert(monthlyCounter)
        val habits = habitRepository.getAllHabitsThatAreMonthly()
        val monthlyHabits = habitToPeriodicityHabitMapper.toMonthlyHabits(habits, false, monthlyCounter.period)
        monthlyHabits.forEach { monthlyHabit ->
            monthlyHabit.id = 0
            monthlyHabit.period = monthlyCounter.period
            monthlyHabitRepository.save(monthlyHabit)
        }
    }
}