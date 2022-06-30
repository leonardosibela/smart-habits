package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.sibela.smarthabits.domain.repository.DailyHabitRepository
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository

class ResetDailyHabitsUseCase(
    private val habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper,
    private val habitRepository: HabitRepository,
    private val dailyHabitRepository: DailyHabitRepository,
    private val habitCounterRepository: HabitCounterRepository
) {

    suspend operator fun invoke() {
        val habits = habitRepository.getAllHabitsThatAreDaily().also {
            if (it.isEmpty()) return
        }

        val nextDailyCounter = habitCounterRepository.getLastDailyCounter().apply {
            id = 0
            period++
        }

        habitCounterRepository.insert(nextDailyCounter)

        val dailyHabits = habitToPeriodicityHabitMapper.toDailyHabits(
            habits, false, nextDailyCounter.period
        )

        dailyHabits.forEach { dailyHabit ->
            dailyHabit.id = 0
            dailyHabit.period = nextDailyCounter.period
            dailyHabitRepository.save(dailyHabit)
        }
    }
}