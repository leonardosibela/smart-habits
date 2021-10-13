package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.MonthlyHabitDao
import com.sibela.smarthabits.data.mapper.MonthlyHabitMapper
import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.repository.MonthlyHabitRepository

class MonthlyHabitRepositoryImpl(
    private val monthlyHabitDao: MonthlyHabitDao,
    private val monthlyHabitMapper: MonthlyHabitMapper
) : MonthlyHabitRepository {

    override suspend fun save(monthlyHabit: MonthlyHabit) {
        monthlyHabitDao.insert(monthlyHabitMapper.fromDomain(monthlyHabit))
    }

    override suspend fun getHabitsForPeriod(period: Int) =
        monthlyHabitMapper.toDomainList(monthlyHabitDao.getHabitsForPeriod(period))

    override suspend fun remove(monthlyHabit: MonthlyHabit) =
        monthlyHabitDao.delete(monthlyHabitMapper.fromDomain(monthlyHabit))

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