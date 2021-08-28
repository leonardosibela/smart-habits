package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.HabitCounterDao
import com.sibela.smarthabits.domain.model.HabitCounter
import com.sibela.smarthabits.domain.repository.HabitCounterRepository

class HabitCounterRepositoryImpl(
    private val habitCounterDao: HabitCounterDao
) : HabitCounterRepository {

    override suspend fun getLastDailyCounter() = habitCounterDao.getLastDailyHabitCounter()
    override suspend fun getLastWeeklyCounter() = habitCounterDao.getLastWeeklyHabitCounter()
    override suspend fun getLastMonthlyCounter() = habitCounterDao.getLastMonthlyHabitCounter()
    override suspend fun getLastYearlyCounter() = habitCounterDao.getLastYearlyHabitCounter()
    override suspend fun update(habitCounter: HabitCounter) = habitCounterDao.update(habitCounter)
}