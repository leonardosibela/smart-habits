package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitMapper
import com.sibela.smarthabits.domain.repository.DailyHabitRepository
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository

class ResetDailyHabitsUseCase(
    private val habitMapper: HabitMapper,
    private val habitRepository: HabitRepository,
    private val dailyHabitRepository: DailyHabitRepository,
    private val habitCounterRepository: HabitCounterRepository
) {

    internal suspend operator fun invoke() {
        val dailyCounter = habitCounterRepository.getLastDailyCounter()
        dailyCounter.id = 0
        dailyCounter.period = dailyCounter.period++
        habitCounterRepository.insert(dailyCounter)
        val habits = habitRepository.getAllHabitsThatAreDaily()
        val dailyHabits = habitMapper.toDailyHabits(habits, false, dailyCounter.period)
        dailyHabits.forEach { dailyHabit ->
            dailyHabit.id = 0
            dailyHabit.period = dailyCounter.period
            dailyHabitRepository.save(dailyHabit)
        }
    }
}