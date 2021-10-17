package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.domain.repository.WeeklyHabitRepository

class ResetWeeklyHabitsUseCase(
    private val habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper,
    private val habitRepository: HabitRepository,
    private val weeklyHabitRepository: WeeklyHabitRepository,
    private val habitCounterRepository: HabitCounterRepository
) {

    suspend operator fun invoke() {
        val weeklyCounter = habitCounterRepository.getLastWeeklyCounter()
        weeklyCounter.id = 0
        weeklyCounter.period = weeklyCounter.period++
        habitCounterRepository.insert(weeklyCounter)
        val habits = habitRepository.getAllHabitsThatAreWeekly()
        val weeklyHabits = habitToPeriodicityHabitMapper.toWeeklyHabits(habits, false, weeklyCounter.period)
        weeklyHabits.forEach { weeklyHabit ->
            weeklyHabit.id = 0
            weeklyHabit.period = weeklyCounter.period
            weeklyHabitRepository.save(weeklyHabit)
        }
    }
}