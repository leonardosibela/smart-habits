package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.YearlyHabitDao
import com.sibela.smarthabits.data.mapper.YearlyHabitMapper
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.PeriodicHabitRepository

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