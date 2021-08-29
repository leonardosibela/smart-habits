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

    internal suspend operator fun invoke(habit: Habit) {
        habitRepository.delete(habit)
        when (habit.periodicity) {
            Periodicity.DAILY -> dailyHabitRepository.removeNotCompletedById(habit.id)
            Periodicity.WEEKLY -> weeklyHabitRepository.removeNotCompletedById(habit.id)
            Periodicity.MONTHLY -> monthlyHabitRepository.removeNotCompletedById(habit.id)
            Periodicity.YEARLY -> yearlyHabitRepository.removeNotCompletedById(habit.id)
        }
    }
}