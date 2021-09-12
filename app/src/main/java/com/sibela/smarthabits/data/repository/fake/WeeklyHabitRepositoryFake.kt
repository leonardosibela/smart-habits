package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.repository.WeeklyHabitRepository

class WeeklyHabitRepositoryFake : WeeklyHabitRepository {

    override suspend fun save(weeklyHabit: WeeklyHabit) {

    }

    override suspend fun getHabitsForPeriod(period: Int) = listOf(
        WeeklyHabit(0, "Read some pages of a book", false, 1),
        WeeklyHabit(0, "Exercise for at last 30 min", false, 1),
    )

    override suspend fun remove(weeklyHabit: WeeklyHabit) {

    }

    override suspend fun removeNotCompletedByDescription(description: String) {

    }

    override suspend fun updateNotCompletedDescription(id: Int, newDescription: String) {

    }

    override suspend fun updateNotCompletedDescription(
        oldDescription: String, newDescription: String
    ) {

    }
}