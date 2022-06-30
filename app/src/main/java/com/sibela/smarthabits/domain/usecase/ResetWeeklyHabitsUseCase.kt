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
        val habits = habitRepository.getAllHabitsThatAreWeekly().also {
            if (it.isEmpty()) return
        }

        val nextWeeklyCounter = habitCounterRepository.getLastWeeklyCounter().apply {
            id = 0
            period++
        }

        habitCounterRepository.insert(nextWeeklyCounter)

        val weeklyHabits = habitToPeriodicityHabitMapper.toWeeklyHabits(
            habits, false, nextWeeklyCounter.period
        )

        weeklyHabits.forEach { weeklyHabit ->
            weeklyHabit.id = 0
            weeklyHabit.period = nextWeeklyCounter.period
            weeklyHabitRepository.save(weeklyHabit)
        }
    }
}