package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.repository.*

class DeleteHabitUseCase(
    private val habitRepository: HabitRepository,
    private val dailyHabitRepository: DailyHabitRepository,
    private val weeklyHabitRepository: WeeklyHabitRepository,
    private val monthlyHabitRepository: MonthlyHabitRepository,
    private val yearlyHabitRepository: YearlyHabitRepository
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