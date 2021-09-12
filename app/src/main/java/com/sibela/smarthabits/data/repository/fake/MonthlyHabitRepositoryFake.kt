package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.repository.MonthlyHabitRepository

class MonthlyHabitRepositoryFake : MonthlyHabitRepository {

    override suspend fun save(monthlyHabit: MonthlyHabit) {

    }

    override suspend fun getHabitsForPeriod(period: Int) = listOf(
        MonthlyHabit(0, "Read some pages of a book", false, 1),
        MonthlyHabit(0, "Exercise for at last 30 min", false, 1),
    )

    override suspend fun remove(monthlyHabit: MonthlyHabit) {

    }

    override suspend fun removeNotCompletedByDescription(description: String) {

    }
}