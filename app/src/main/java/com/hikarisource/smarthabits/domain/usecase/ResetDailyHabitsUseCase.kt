package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.hikarisource.smarthabits.domain.model.DailyHabit
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.domain.repository.HabitRepository
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository

class ResetDailyHabitsUseCase(
    private val habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper,
    private val habitRepository: HabitRepository,
    private val dailyHabitRepository: PeriodicHabitRepository<DailyHabit>,
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
            habits = habits,
            completed = false,
            period = nextDailyCounter.period
        )

        dailyHabits.forEach { dailyHabit ->
            dailyHabit.id = 0
            dailyHabit.period = nextDailyCounter.period
            dailyHabitRepository.save(dailyHabit)
        }
    }
}
