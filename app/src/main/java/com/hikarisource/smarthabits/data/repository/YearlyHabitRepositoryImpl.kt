package com.hikarisource.smarthabits.data.repository

import com.hikarisource.smarthabits.data.local.YearlyHabitDao
import com.hikarisource.smarthabits.data.mapper.YearlyHabitMapper
import com.hikarisource.smarthabits.domain.model.YearlyHabit
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository

class YearlyHabitRepositoryImpl(
    private val yearlyHabitDao: YearlyHabitDao,
    private val yearlyHabitMapper: YearlyHabitMapper,
    private val habitCounterRepository: HabitCounterRepository,
) : PeriodicHabitRepository<YearlyHabit> {

    override suspend fun save(habit: YearlyHabit) {
        yearlyHabitDao.insert(yearlyHabitMapper.fromDomain(habit))
    }

    override suspend fun getHabitsForLastPeriod(): List<YearlyHabit> {
        val habitCounter = habitCounterRepository.getLastYearlyCounter()
        return yearlyHabitMapper.toDomainList(yearlyHabitDao.getHabitsForPeriod(habitCounter.period))
    }

    override suspend fun remove(habit: YearlyHabit) =
        yearlyHabitDao.delete(yearlyHabitMapper.fromDomain(habit))

    override suspend fun removeNotCompletedByDescription(description: String) =
        yearlyHabitDao.deleteNotCompletedByDescription(description)

    override suspend fun updateNotCompletedDescription(id: Int, newDescription: String) {
        yearlyHabitDao.updateNotCompletedDescription(id, newDescription)
    }

    override suspend fun updateNotCompletedDescription(
        oldDescription: String, newDescription: String,
    ) {
        yearlyHabitDao.updateNotCompletedDescription(oldDescription, newDescription)
    }
}