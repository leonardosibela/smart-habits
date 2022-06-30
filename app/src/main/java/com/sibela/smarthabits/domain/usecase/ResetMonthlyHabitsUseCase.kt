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