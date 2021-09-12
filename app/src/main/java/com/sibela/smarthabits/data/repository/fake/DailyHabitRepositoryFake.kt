package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.repository.DailyHabitRepository

class DailyHabitRepositoryFake : DailyHabitRepository {

    override suspend fun save(dailyHabit: DailyHabit) {

    }

    override suspend fun getHabitsForPeriod(period: Int) =
        listOf(
            DailyHabit(0, "Read some pages of a book", false, 1),
            DailyHabit(0, "Exercise for at last 30 min", false, 1),
        )

    override suspend fun remove(dailyHabit: DailyHabit) {

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