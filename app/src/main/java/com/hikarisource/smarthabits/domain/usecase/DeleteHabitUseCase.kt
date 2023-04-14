package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.DailyHabit
import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.domain.model.MonthlyHabit
import com.hikarisource.smarthabits.domain.model.Periodicity
import com.hikarisource.smarthabits.domain.model.WeeklyHabit
import com.hikarisource.smarthabits.domain.model.YearlyHabit
import com.hikarisource.smarthabits.domain.repository.HabitRepository
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository

class DeleteHabitUseCase(
    private val habitRepository: HabitRepository,
    private val dailyHabitRepository: PeriodicHabitRepository<DailyHabit>,
    private val weeklyHabitRepository: PeriodicHabitRepository<WeeklyHabit>,
    private val monthlyHabitRepository: PeriodicHabitRepository<MonthlyHabit>,
    private val yearlyHabitRepository: PeriodicHabitRepository<YearlyHabit>,
) {

    suspend operator fun invoke(habit: Habit) {
        habitRepository.delete(habit)
        when (habit.periodicity) {
            Periodicity.DAILY -> dailyHabitRepository.removeNotCompletedByDescription(habit.description)
            Periodicity.WEEKLY -> weeklyHabitRepository.removeNotCompletedByDescription(habit.description)
            Periodicity.MONTHLY -> monthlyHabitRepository.removeNotCompletedByDescription(habit.description)
            Periodicity.YEARLY -> yearlyHabitRepository.removeNotCompletedByDescription(habit.description)
        }
        return Unit
    }
}