package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.HabitDao
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.repository.HabitRepository

class HabitRepositoryImpl(
    private val habitDao: HabitDao,
) : HabitRepository {

    override suspend fun save(habit: Habit) {
        habitDao.insert(habit)
    }

    override suspend fun getAllHabitsThatAreDaily() = habitDao.getAllThatAreDaily()

    override suspend fun getAllHabitsThatAreWeekly() = habitDao.getAllThatAreWeekly()

    override suspend fun getAllHabitsThatAreMonthly() = habitDao.getAllThatAreMonthly()

    override suspend fun getAllHabitsThatAreYearly() = habitDao.getAllThatAreYearly()

    override suspend fun delete(habit: Habit) = habitDao.delete(habit)

    override suspend fun editHabitDescription(id: Int, newDescription: String) =
        habitDao.updateDescription(id, newDescription)
}