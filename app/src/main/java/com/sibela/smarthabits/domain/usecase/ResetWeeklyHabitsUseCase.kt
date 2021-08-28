package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitMapper
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.domain.repository.WeeklyHabitRepository

class ResetWeeklyHabitsUseCase(
    private val habitMapper: HabitMapper,
    private val habitRepository: HabitRepository,
    private val weeklyHabitRepository: WeeklyHabitRepository,
    private val habitCounterRepository: HabitCounterRepository
) {

    internal suspend operator fun invoke() {
        val weeklyCounter = habitCounterRepository.getLastWeeklyCounter()
        weeklyCounter.period = weeklyCounter.period++
        habitCounterRepository.update(weeklyCounter)
        val habits = habitRepository.getAllHabitsThatAreWeekly()
        val weeklyHabits = habitMapper.toWeeklyHabits(habits, false, weeklyCounter.period)
        weeklyHabits.forEach { weeklyHabit ->
            weeklyHabit.id = 0
            weeklyHabit.period = weeklyCounter.period
            weeklyHabitRepository.save(weeklyHabit)
        }
    }
}