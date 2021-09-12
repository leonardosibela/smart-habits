package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.MonthlyHabitDao
import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.repository.MonthlyHabitRepository

class MonthlyHabitRepositoryImpl(
    private val monthlyHabitDao: MonthlyHabitDao
) : MonthlyHabitRepository {


    override suspend fun save(monthlyHabit: MonthlyHabit) {
        monthlyHabitDao.insert(monthlyHabit)
    }

    override suspend fun getHabitsForPeriod(period: Int) = monthlyHabitDao.getHabitsForPeriod(period)

    override suspend fun remove(monthlyHabit: MonthlyHabit) = monthlyHabitDao.delete(monthlyHabit)

    override suspend fun removeNotCompletedByDescription(description: String) =
        monthlyHabitDao.deleteNotCompletedByDescription(description)
}