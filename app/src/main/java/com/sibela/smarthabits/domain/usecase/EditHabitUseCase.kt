package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.domain.repository.PeriodicHabitRepository

class EditHabitUseCase(
    private val habitRepository: HabitRepository,
    private val dailyHabitRepository: PeriodicHabitRepository<DailyHabit>,
    private val weeklyHabitRepository: PeriodicHabitRepository<WeeklyHabit>,
    private val monthlyHabitRepository: PeriodicHabitRepository<MonthlyHabit>,
    private val yearlyHabitRepository: PeriodicHabitRepository<YearlyHabit>
) {

    suspend operator fun invoke(habit: Habit, newDescription: String) {
        habitRepository.editHabitDescription(habit.id, newDescription)
        when (habit.periodicity) {
            Periodicity.DAILY -> updateNotCompletedDailyHabitDescription(
                habit.description, newDescription
            )
            Periodicity.WEEKLY -> updateNotCompletedWeeklyHabitDescription(
                habit.description, newDescription
            )
            Periodicity.MONTHLY -> updateNotCompletedMonthlyHabitDescription(
                habit.description, newDescription
            )
            Periodicity.YEARLY -> updateNotCompletedYearlyHabitDescription(
                habit.description, newDescription
            )
        }
    }

    private suspend fun updateNotCompletedDailyHabitDescription(
        oldDescription: String, newDescription: String
    ) = dailyHabitRepository.updateNotCompletedDescription(oldDescription, newDescription)

    private suspend fun updateNotCompletedWeeklyHabitDescription(
        oldDescription: String, newDescription: String
    ) = weeklyHabitRepository.updateNotCompletedDescription(oldDescription, newDescription)

    private suspend fun updateNotCompletedMonthlyHabitDescription(
        oldDescription: String, newDescription: String
    ) = monthlyHabitRepository.updateNotCompletedDescription(oldDescription, newDescription)

    private suspend fun updateNotCompletedYearlyHabitDescription(
        oldDescription: String, newDescription: String
    ) = yearlyHabitRepository.updateNotCompletedDescription(oldDescription, newDescription)
}