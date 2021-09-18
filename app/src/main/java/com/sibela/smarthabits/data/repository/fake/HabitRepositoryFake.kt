package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.repository.HabitRepository

class HabitRepositoryFake : HabitRepository {

    private companion object {
        val habits = arrayListOf(
            Habit(1, "Read some pages of a book", Periodicity.DAILY),
            Habit(2, "Exercise for at last 30 min", Periodicity.DAILY),
            Habit(3, "Read some pages of a book", Periodicity.WEEKLY),
            Habit(4, "Exercise for at last 30 min", Periodicity.WEEKLY),
            Habit(5, "Read some pages of a book", Periodicity.MONTHLY),
            Habit(6, "Exercise for at last 30 min", Periodicity.MONTHLY),
            Habit(7, "Read some pages of a book", Periodicity.YEARLY),
            Habit(8, "Exercise for at last 30 min", Periodicity.YEARLY),
        )
    }

    override suspend fun save(habit: Habit) {
        habits.add(habit)
    }

    override suspend fun getAllHabitsThatAreDaily() =
        habits.filter { it.periodicity == Periodicity.DAILY }

    override suspend fun getAllHabitsThatAreWeekly() =
        habits.filter { it.periodicity == Periodicity.WEEKLY }

    override suspend fun getAllHabitsThatAreMonthly() =
        habits.filter { it.periodicity == Periodicity.MONTHLY }

    override suspend fun getAllHabitsThatAreYearly() =
        habits.filter { it.periodicity == Periodicity.YEARLY }

    override suspend fun delete(habit: Habit) {
        habits.remove(habit)
    }

    override suspend fun editHabitDescription(id: Int, newDescription: String) {
        val index: Int = habits.indexOfFirst {
            it.id == id
        }
        val habit = habits[index]
        val updatedHabit = Habit(habit.id, newDescription, habit.periodicity)
        habits.removeAt(index)
        habits.add(index, updatedHabit)
    }
}