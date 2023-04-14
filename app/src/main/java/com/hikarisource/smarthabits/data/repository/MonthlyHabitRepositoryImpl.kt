package com.hikarisource.smarthabits.data.repository

import com.hikarisource.smarthabits.data.local.MonthlyHabitDao
import com.hikarisource.smarthabits.data.mapper.MonthlyHabitMapper
import com.hikarisource.smarthabits.domain.model.MonthlyHabit
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository

class MonthlyHabitRepositoryImpl(
    private val monthlyHabitDao: MonthlyHabitDao,
    private val monthlyHabitMapper: MonthlyHabitMapper,
    private val habitCounterRepository: HabitCounterRepository,
) : PeriodicHabitRepository<MonthlyHabit> {

    override suspend fun save(habit: MonthlyHabit) {
        monthlyHabitDao.insert(monthlyHabitMapper.fromDomain(habit))
    }

    override suspend fun getHabitsForLastPeriod(): List<MonthlyHabit> {
        val habitCounter = habitCounterRepository.getLastMonthlyCounter()
        return monthlyHabitMapper.toDomainList(monthlyHabitDao.getHabitsForPeriod(habitCounter.period))
    }

    override suspend fun remove(habit: MonthlyHabit) =
        monthlyHabitDao.delete(monthlyHabitMapper.fromDomain(habit))

    override suspend fun removeNotCompletedByDescription(description: String) =
        monthlyHabitDao.deleteNotCompletedByDescription(description)

    override suspend fun updateNotCompletedDescription(id: Int, newDescription: String) {
        monthlyHabitDao.updateNotCompletedDescription(id, newDescription)
    }

    override suspend fun updateNotCompletedDescription(
        oldDescription: String, newDescription: String,
    ) {
        monthlyHabitDao.updateNotCompletedDescription(oldDescription, newDescription)
    }
}