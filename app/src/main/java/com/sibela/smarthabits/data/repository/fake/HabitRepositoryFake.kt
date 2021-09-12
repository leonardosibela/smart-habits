package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.repository.HabitRepository

class HabitRepositoryFake : HabitRepository {

    override suspend fun save(habit: Habit) {

    }

    override suspend fun getAllHabitsThatAreDaily() = listOf(
        Habit(0, "Read some pages of a book", Periodicity.DAILY),
        Habit(0, "Exercise for at last 30 min", Periodicity.DAILY),
    )

    override suspend fun getAllHabitsThatAreWeekly() = listOf(
        Habit(0, "Read some pages of a book", Periodicity.WEEKLY),
        Habit(0, "Exercise for at last 30 min", Periodicity.WEEKLY),
    )

    override suspend fun getAllHabitsThatAreMonthly() = listOf(
        Habit(0, "Read some pages of a book", Periodicity.MONTHLY),
        Habit(0, "Exercise for at last 30 min", Periodicity.MONTHLY),
    )

    override suspend fun getAllHabitsThatAreYearly() = listOf(
        Habit(0, "Read some pages of a book", Periodicity.YEARLY),
        Habit(0, "Exercise for at last 30 min", Periodicity.YEARLY),
    )

    override suspend fun delete(habit: Habit) {

    }

    override suspend fun editHabitDescription(id: Int, newDescription: String) {

    }
}