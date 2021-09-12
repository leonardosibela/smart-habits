package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.repository.YearlyHabitRepository

class YearlyHabitRepositoryFake : YearlyHabitRepository {

    override suspend fun save(yearlyHabit: YearlyHabit) {

    }

    override suspend fun getHabitsForPeriod(period: Int) = listOf(
        YearlyHabit(0, "Read some pages of a book", false, 1),
        YearlyHabit(0, "Exercise for at last 30 min", false, 1),
    )

    override suspend fun remove(yearlyHabit: YearlyHabit) {

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