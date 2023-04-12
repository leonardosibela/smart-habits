package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.WeeklyHabitDao
import com.sibela.smarthabits.data.mapper.WeeklyHabitMapper
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.PeriodicHabitRepository

class WeeklyHabitRepositoryImpl(
    private val weeklyHabitDao: WeeklyHabitDao,
    private val weeklyHabitMapper: WeeklyHabitMapper,
    private val habitCounterRepository: HabitCounterRepository
) : PeriodicHabitRepository<WeeklyHabit> {

    override suspend fun save(habit: WeeklyHabit) {
        weeklyHabitDao.insert(weeklyHabitMapper.fromDomain(habit))
    }

    override suspend fun getHabitsForLastPeriod(): List<WeeklyHabit> {
        val habitCounter = habitCounterRepository.getLastWeeklyCounter()
        return weeklyHabitMapper.toDomainList(weeklyHabitDao.getHabitsForPeriod(habitCounter.period))
    }

    override suspend fun remove(habit: WeeklyHabit) =
        weeklyHabitDao.delete(weeklyHabitMapper.fromDomain(habit))

    override suspend fun removeNotCompletedByDescription(description: String) =
        weeklyHabitDao.deleteNotCompletedByDescription(description)

    override suspend fun updateNotCompletedDescription(id: Int, newDescription: String) {
        weeklyHabitDao.updateNotCompletedDescription(id, newDescription)
    }

    override suspend fun updateNotCompletedDescription(
        oldDescription: String, newDescription: String,
    ) {
        weeklyHabitDao.updateNotCompletedDescription(oldDescription, newDescription)
    }
}