package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.domain.repository.PeriodicHabitRepository

class AddHabitUseCase(
    private val habitRepository: HabitRepository,
    private val habitCounterRepository: HabitCounterRepository,
    private val dailyHabitRepository: PeriodicHabitRepository<DailyHabit>,
    private val weeklyHabitRepository: PeriodicHabitRepository<WeeklyHabit>,
    private val monthlyHabitRepository: PeriodicHabitRepository<MonthlyHabit>,
    private val yearlyHabitRepository: PeriodicHabitRepository<YearlyHabit>,
) {

    suspend operator fun invoke(description: String, periodicity: Periodicity) {
        habitRepository.save(Habit(description = description, periodicity = periodicity))
        when (periodicity) {
            Periodicity.DAILY -> saveDailyHabit(description)
            Periodicity.WEEKLY -> saveWeeklyHabit(description)
            Periodicity.MONTHLY -> saveMonthlyHabit(description)
            Periodicity.YEARLY -> saveYearlyHabit(description)
        }
    }

    private suspend fun saveDailyHabit(description: String) {
        val lastDailyCounter = habitCounterRepository.getLastDailyCounter()
        val dailyHabit = DailyHabit(
            description = description,
            completed = false,
            period = lastDailyCounter.period
        )
        dailyHabitRepository.save(dailyHabit)
    }

    private suspend fun saveWeeklyHabit(description: String) {
        val lastWeeklyCounter = habitCounterRepository.getLastWeeklyCounter()
        val weeklyHabit = WeeklyHabit(
            description = description,
            completed = false,
            period = lastWeeklyCounter.period
        )
        weeklyHabitRepository.save(weeklyHabit)
    }

    private suspend fun saveMonthlyHabit(description: String) {
        val lastWeeklyCounter = habitCounterRepository.getLastMonthlyCounter()
        val monthlyHabit = MonthlyHabit(
            description = description,
            completed = false,
            period = lastWeeklyCounter.period
        )
        monthlyHabitRepository.save(monthlyHabit)
    }

    private suspend fun saveYearlyHabit(description: String) {
        val lastYearlyCounter = habitCounterRepository.getLastYearlyCounter()
        val yearlyHabit = YearlyHabit(
            description = description,
            completed = false,
            period = lastYearlyCounter.period
        )
        yearlyHabitRepository.save(yearlyHabit)
    }
}