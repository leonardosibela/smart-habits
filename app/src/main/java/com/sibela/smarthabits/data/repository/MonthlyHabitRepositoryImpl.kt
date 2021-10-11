package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.MonthlyHabitDao
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

    override suspend fun updateNotCompletedDescription(id: Int, newDescription: String) {
        monthlyHabitDao.updateNotCompletedDescription(id, newDescription)
    }

    override suspend fun updateNotCompletedDescription(
        oldDescription: String, newDescription: String
    ) {
        monthlyHabitDao.updateNotCompletedDescription(oldDescription, newDescription)
    }
}