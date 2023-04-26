package com.hikarisource.smarthabits.data.repository

import com.hikarisource.smarthabits.data.local.HabitDao
import com.hikarisource.smarthabits.data.mapper.HabitMapper
import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.domain.repository.HabitRepository

class HabitRepositoryImpl(
    private val habitDao: HabitDao,
    private val habitMapper: HabitMapper
) : HabitRepository {

    override suspend fun save(habit: Habit) {
        habitDao.insert(habitMapper.fromDomain(habit))
    }

    override suspend fun getAllHabitsThatAreDaily() =
        habitMapper.toDomainList(habitDao.getAllThatAreDaily())

    override suspend fun getAllHabitsThatAreWeekly() =
        habitMapper.toDomainList(habitDao.getAllThatAreWeekly())

    override suspend fun getAllHabitsThatAreMonthly() =
        habitMapper.toDomainList(habitDao.getAllThatAreMonthly())

    override suspend fun getAllHabitsThatAreYearly() =
        habitMapper.toDomainList(habitDao.getAllThatAreYearly())

    override suspend fun delete(habit: Habit) = habitDao.delete(habitMapper.fromDomain(habit))

    override suspend fun editHabitDescription(id: Int, newDescription: String) =
        habitDao.updateDescription(id, newDescription)
}
