package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.YearlyHabitDao
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.repository.YearlyHabitRepository

class YearlyHabitRepositoryImpl(
    private val yearlyHabitDao: YearlyHabitDao
) : YearlyHabitRepository {

    override suspend fun save(yearlyHabit: YearlyHabit) {
        yearlyHabitDao.insert(yearlyHabit)
    }

    override suspend fun getHabitsForPeriod(period: Int) = yearlyHabitDao.getHabitsForPeriod(period)

    override suspend fun remove(yearlyHabit: YearlyHabit) = yearlyHabitDao.delete(yearlyHabit)

    override suspend fun removeNotCompletedById(id: Int) = yearlyHabitDao.deleteNotCompletedById(id)
}