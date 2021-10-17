package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.repository.*

class EditHabitUseCase(
    private val habitRepository: HabitRepository,
    private val dailyHabitRepository: DailyHabitRepository,
    private val weeklyHabitRepository: WeeklyHabitRepository,
    private val monthlyHabitRepository: MonthlyHabitRepository,
    private val yearlyHabitRepository: YearlyHabitRepository
) {

    suspend operator fun invoke(habit: Habit, newDescription: String) {
        habitRepository.editHabitDescription(habit.id, newDescription)
        when (habit.periodicity) {
            Periodicity.DAILY -> {
                dailyHabitRepository.updateNotCompletedDescription(
                    habit.description,
                    newDescription
                )
            }
            Periodicity.WEEKLY -> {
                weeklyHabitRepository.updateNotCompletedDescription(
                    habit.description,
                    newDescription
                )
            }
            Periodicity.MONTHLY -> {
                monthlyHabitRepository.updateNotCompletedDescription(
                    habit.description,
                    newDescription
                )
            }
            Periodicity.YEARLY -> {
                yearlyHabitRepository.updateNotCompletedDescription(
                    habit.description,
                    newDescription
                )
            }
        }
    }
}