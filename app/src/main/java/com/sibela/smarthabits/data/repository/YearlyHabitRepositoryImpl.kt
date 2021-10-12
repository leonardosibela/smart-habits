package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.YearlyHabitDao
import com.sibela.smarthabits.data.mapper.YearlyHabitMapper
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.repository.YearlyHabitRepository

class YearlyHabitRepositoryImpl(
    private val yearlyHabitDao: YearlyHabitDao,
    private val yearlyHabitMapper: YearlyHabitMapper
) : YearlyHabitRepository {

    override suspend fun save(yearlyHabit: YearlyHabit) {
        yearlyHabitDao.insert(yearlyHabitMapper.fromDomain(yearlyHabit))
    }

    override suspend fun getHabitsForPeriod(period: Int) =
        yearlyHabitMapper.toDomainList(yearlyHabitDao.getHabitsForPeriod(period))

    override suspend fun remove(yearlyHabit: YearlyHabit) =
        yearlyHabitDao.delete(yearlyHabitMapper.fromDomain(yearlyHabit))

    override suspend fun removeNotCompletedByDescription(description: String) =
        yearlyHabitDao.deleteNotCompletedByDescription(description)

    override suspend fun updateNotCompletedDescription(id: Int, newDescription: String) {
        yearlyHabitDao.updateNotCompletedDescription(id, newDescription)
    }

    override suspend fun updateNotCompletedDescription(
        oldDescription: String, newDescription: String
    ) {
        yearlyHabitDao.updateNotCompletedDescription(oldDescription, newDescription)
    }
}