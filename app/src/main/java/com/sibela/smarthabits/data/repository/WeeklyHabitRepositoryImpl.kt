package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.WeeklyHabitDao
import com.sibela.smarthabits.data.mapper.WeeklyHabitMapper
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.repository.WeeklyHabitRepository

class WeeklyHabitRepositoryImpl(
    private val weeklyHabitDao: WeeklyHabitDao,
    private val weeklyHabitMapper: WeeklyHabitMapper
) : WeeklyHabitRepository {

    override suspend fun save(weeklyHabit: WeeklyHabit) {
        weeklyHabitDao.insert(weeklyHabitMapper.fromDomain(weeklyHabit))
    }

    override suspend fun getHabitsForPeriod(period: Int) =
        weeklyHabitMapper.toDomainList(weeklyHabitDao.getHabitsForPeriod(period))

    override suspend fun remove(weeklyHabit: WeeklyHabit) =
        weeklyHabitDao.delete(weeklyHabitMapper.fromDomain(weeklyHabit))

    override suspend fun removeNotCompletedByDescription(description: String) =
        weeklyHabitDao.deleteNotCompletedByDescription(description)

    override suspend fun updateNotCompletedDescription(id: Int, newDescription: String) {
        weeklyHabitDao.updateNotCompletedDescription(id, newDescription)
    }

    override suspend fun updateNotCompletedDescription(
        oldDescription: String, newDescription: String
    ) {
        weeklyHabitDao.updateNotCompletedDescription(oldDescription, newDescription)
    }
}