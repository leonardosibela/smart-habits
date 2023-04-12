package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.DailyHabitDao
import com.sibela.smarthabits.data.mapper.DailyHabitMapper
import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.PeriodicHabitRepository

class DailyHabitRepositoryImpl(
    private val dailyHabitDao: DailyHabitDao,
    private val dailyHabitMapper: DailyHabitMapper,
    private val habitCounterRepository: HabitCounterRepository,
) : PeriodicHabitRepository<DailyHabit> {

    override suspend fun save(habit: DailyHabit) {
        dailyHabitDao.insert(dailyHabitMapper.fromDomain(habit))
    }

    override suspend fun getHabitsForLastPeriod(): List<DailyHabit> {
        val habitCounter = habitCounterRepository.getLastDailyCounter()
        return dailyHabitMapper.toDomainList(dailyHabitDao.getHabitsForPeriod(habitCounter.period))
    }

    override suspend fun remove(habit: DailyHabit) =
        dailyHabitDao.delete(dailyHabitMapper.fromDomain(habit))

    override suspend fun removeNotCompletedByDescription(description: String) =
        dailyHabitDao.deleteNotCompletedByDescription(description)

    override suspend fun updateNotCompletedDescription(id: Int, newDescription: String) {
        dailyHabitDao.updateNotCompletedDescription(id, newDescription)
    }

    override suspend fun updateNotCompletedDescription(
        oldDescription: String, newDescription: String,
    ) {
        dailyHabitDao.updateNotCompletedDescription(oldDescription, newDescription)
    }
}