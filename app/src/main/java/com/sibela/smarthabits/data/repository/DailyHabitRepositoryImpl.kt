package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.DailyHabitDao
import com.sibela.smarthabits.data.mapper.DailyHabitMapper
import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.repository.DailyHabitRepository

class DailyHabitRepositoryImpl(
    private val dailyHabitDao: DailyHabitDao,
    private val dailyHabitMapper: DailyHabitMapper
) : DailyHabitRepository {

    override suspend fun save(dailyHabit: DailyHabit) {
        dailyHabitDao.insert(dailyHabitMapper.fromDomain(dailyHabit))
    }

    override suspend fun getHabitsForPeriod(period: Int) =
        dailyHabitMapper.toDomainList(dailyHabitDao.getHabitsForPeriod(period))

    override suspend fun remove(dailyHabit: DailyHabit) =
        dailyHabitDao.delete(dailyHabitMapper.fromDomain(dailyHabit))

    override suspend fun removeNotCompletedByDescription(description: String) =
        dailyHabitDao.deleteNotCompletedByDescription(description)

    override suspend fun updateNotCompletedDescription(id: Int, newDescription: String) {
        dailyHabitDao.updateNotCompletedDescription(id, newDescription)
    }

    override suspend fun updateNotCompletedDescription(
        oldDescription: String, newDescription: String
    ) {
        dailyHabitDao.updateNotCompletedDescription(oldDescription, newDescription)
    }
}