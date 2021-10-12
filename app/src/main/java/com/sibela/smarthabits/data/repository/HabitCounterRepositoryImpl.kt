package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.HabitCounterDao
import com.sibela.smarthabits.data.mapper.HabitCounterMapper
import com.sibela.smarthabits.domain.model.HabitCounter
import com.sibela.smarthabits.domain.repository.HabitCounterRepository

class HabitCounterRepositoryImpl(
    private val habitCounterDao: HabitCounterDao,
    private val habitCounterMapper: HabitCounterMapper
) : HabitCounterRepository {

    override suspend fun getLastDailyCounter() =
        habitCounterMapper.toDomain(habitCounterDao.getLastDailyHabitCounter())

    override suspend fun getLastWeeklyCounter() =
        habitCounterMapper.toDomain(habitCounterDao.getLastWeeklyHabitCounter())

    override suspend fun getLastMonthlyCounter() =
        habitCounterMapper.toDomain(habitCounterDao.getLastMonthlyHabitCounter())

    override suspend fun getLastYearlyCounter() =
        habitCounterMapper.toDomain(habitCounterDao.getLastYearlyHabitCounter())

    override suspend fun insert(habitCounter: HabitCounter) =
        habitCounterDao.insert(habitCounterMapper.fromDomain(habitCounter))
}