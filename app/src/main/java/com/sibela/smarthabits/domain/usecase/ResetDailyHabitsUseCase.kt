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
        val dailyCounter = habitCounterRepository.getLastDailyCounter()
        dailyCounter.id = 0
        dailyCounter.period++
        habitCounterRepository.insert(dailyCounter)
        val habits = habitRepository.getAllHabitsThatAreDaily()
        val dailyHabits = habitToPeriodicityHabitMapper.toDailyHabits(habits, false, dailyCounter.period)
        dailyHabits.forEach { dailyHabit ->
            dailyHabit.id = 0
            dailyHabit.period = dailyCounter.period
            dailyHabitRepository.save(dailyHabit)
        }
    }
}