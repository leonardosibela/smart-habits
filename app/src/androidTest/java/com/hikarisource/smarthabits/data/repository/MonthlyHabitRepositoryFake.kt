package com.hikarisource.smarthabits.data.repository

import com.hikarisource.smarthabits.domain.model.MonthlyHabit

class MonthlyHabitRepositoryFake : MonthlyHabitRepository {

    private companion object {
        val habits = arrayListOf(
            MonthlyHabit(1, "Read some pages of a book", true, 1),
            MonthlyHabit(2, "Exercise for at last 30 min", true, 1),
            MonthlyHabit(3, "Read some pages of a book", false, 2),
            MonthlyHabit(4, "Exercise for at last 30 min", false, 2),
        )
    }

    override suspend fun save(monthlyHabit: MonthlyHabit) {
        habits.add(monthlyHabit)
    }

    override suspend fun getHabitsForPeriod(period: Int) = habits.filter { it.period == period }

    override suspend fun remove(monthlyHabit: MonthlyHabit) {
        habits.remove(monthlyHabit)
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
        val updatedHabit = MonthlyHabit(habit.id, newDescription, habit.completed, habit.period)
        habits.removeAt(index)
        habits.add(index, updatedHabit)
    }

    override suspend fun updateNotCompletedDescription(
        oldDescription: String, newDescription: String
    ) {
        val index: Int = habits.indexOfFirst { it.description == oldDescription }
        val habit = habits[index]
        val updatedHabit = MonthlyHabit(habit.id, newDescription, habit.completed, habit.period)
        habits.removeAt(index)
        habits.add(index, updatedHabit)
    }
}