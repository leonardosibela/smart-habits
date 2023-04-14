package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.hikarisource.smarthabits.domain.model.WeeklyHabit
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.domain.repository.HabitRepository
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository

class ResetWeeklyHabitsUseCase(
    private val habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper,
    private val habitRepository: HabitRepository,
    private val weeklyHabitRepository: PeriodicHabitRepository<WeeklyHabit>,
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