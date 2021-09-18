package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.repository.WeeklyHabitRepository

class WeeklyHabitRepositoryFake : WeeklyHabitRepository {

    private companion object {
        val habits = arrayListOf(
            WeeklyHabit(1, "Read some pages of a book", false, 1),
            WeeklyHabit(2, "Exercise for at last 30 min", false, 1),
            WeeklyHabit(3, "Read some pages of a book", false, 2),
            WeeklyHabit(4, "Exercise for at last 30 min", false, 2),
        )
    }

    override suspend fun save(weeklyHabit: WeeklyHabit) {
        habits.add(weeklyHabit)
    }

    override suspend fun getHabitsForPeriod(period: Int) = habits.filter { it.period == period }

    override suspend fun remove(weeklyHabit: WeeklyHabit) {
        habits.remove(weeklyHabit)
    }

    override suspend fun removeNotCompletedByDescription(description: String) {
        val habit = habits.first {
            it.completed.not()
            it.description == description
        }
        habits.remove(habit)
    }

    override suspend fun updateNotCompletedDescription(id: Int, newDescription: String) {
        val index: Int = habits.indexOfFirst {
            it.id == id
        }
        val habit = habits[index]
        val updatedHabit = WeeklyHabit(habit.id, newDescription, habit.completed, habit.period)
        habits.removeAt(index)
        habits.add(index, updatedHabit)
    }

    override suspend fun updateNotCompletedDescription(
        oldDescription: String, newDescription: String
    ) {
        val index: Int = habits.indexOfFirst {
            it.description == oldDescription
        }
        val habit = habits[index]
        val updatedHabit = WeeklyHabit(habit.id, newDescription, habit.completed, habit.period)
        habits.removeAt(index)
        habits.add(index, updatedHabit)
    }
}