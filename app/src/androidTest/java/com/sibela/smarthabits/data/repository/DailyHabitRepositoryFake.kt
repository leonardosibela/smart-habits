package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.repository.DailyHabitRepository

class DailyHabitRepositoryFake : DailyHabitRepository {

    private companion object {
        val habits = arrayListOf(
            DailyHabit(1, "Read some pages of a book", true, 1),
            DailyHabit(2, "Exercise for at last 30 min", true, 1),
            DailyHabit(3, "Read some pages of a book", false, 2),
            DailyHabit(4, "Exercise for at last 30 min", false, 2),
        )
    }

    override suspend fun save(dailyHabit: DailyHabit) {
        habits.add(dailyHabit)
    }

    override suspend fun getHabitsForPeriod(period: Int) = habits.filter { it.period == period }

    override suspend fun remove(dailyHabit: DailyHabit) {
        habits.remove(dailyHabit)
    }

    override suspend fun removeNotCompletedByDescription(description: String) {
        val habit = habits.first {
            it.completed.not()
            it.description == description
        }
        habits.remove(habit)
    }

    override suspend fun updateNotCompletedDescription(id: Int, newDescription: String) {
        val index: Int = habits.indexOfFirst { it.id == id }
        val habit = habits[index]
        val updatedHabit = DailyHabit(habit.id, newDescription, habit.completed, habit.period)
        habits.removeAt(index)
        habits.add(index, updatedHabit)
    }

    override suspend fun updateNotCompletedDescription(
        oldDescription: String, newDescription: String
    ) {
        val index: Int = habits.indexOfFirst { it.description == oldDescription }
        val habit = habits[index]
        val updatedHabit = DailyHabit(habit.id, newDescription, habit.completed, habit.period)
        habits.removeAt(index)
        habits.add(index, updatedHabit)
    }
}