package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.hikarisource.smarthabits.domain.model.MonthlyHabit
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.domain.repository.HabitRepository
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository

class ResetMonthlyHabitsUseCase(
    private val habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper,
    private val habitRepository: HabitRepository,
    private val monthlyHabitRepository: PeriodicHabitRepository<MonthlyHabit>,
    private val habitCounterRepository: HabitCounterRepository
) {

    suspend operator fun invoke() {
        val habits = habitRepository.getAllHabitsThatAreMonthly().also {
            if (it.isEmpty()) return
        }

        val nextMonthlyCounter = habitCounterRepository.getLastMonthlyCounter().apply {
            id = 0
            period++
        }

        habitCounterRepository.insert(nextMonthlyCounter)

        val monthlyHabits = habitToPeriodicityHabitMapper.toMonthlyHabits(
            habits, false, nextMonthlyCounter.period
        )

        monthlyHabits.forEach { monthlyHabit ->
            monthlyHabit.id = 0
            monthlyHabit.period = nextMonthlyCounter.period
            monthlyHabitRepository.save(monthlyHabit)
        }
    }
}