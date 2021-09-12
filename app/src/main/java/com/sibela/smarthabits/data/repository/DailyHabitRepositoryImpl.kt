package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.DailyHabitDao
import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.repository.DailyHabitRepository

class DailyHabitRepositoryImpl(private val dailyHabitDao: DailyHabitDao) : DailyHabitRepository {

    override suspend fun save(dailyHabit: DailyHabit) {
        dailyHabitDao.insert(dailyHabit)
    }

    override suspend fun getHabitsForPeriod(period: Int) = dailyHabitDao.getHabitsForPeriod(period)

    override suspend fun remove(dailyHabit: DailyHabit) = dailyHabitDao.delete(dailyHabit)

    override suspend fun removeNotCompletedById(id: Int) = dailyHabitDao.deleteNotCompletedById(id)
}