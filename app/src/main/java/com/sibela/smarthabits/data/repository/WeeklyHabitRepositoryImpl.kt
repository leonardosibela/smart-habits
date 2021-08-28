package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.WeeklyHabitDao
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.repository.WeeklyHabitRepository

class WeeklyHabitRepositoryImpl(
    private val weeklyHabitDao: WeeklyHabitDao
) : WeeklyHabitRepository {

    override suspend fun save(weeklyHabit: WeeklyHabit) {
        weeklyHabitDao.insert(weeklyHabit)
    }

    override suspend fun getHabitForPeriod(period: Int) = weeklyHabitDao.getHabitsForPeriod(period)

    override suspend fun remove(weeklyHabit: WeeklyHabit) = weeklyHabitDao.delete(weeklyHabit)
}