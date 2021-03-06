package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.DailyHabit

interface DailyHabitRepository {

    suspend fun save(dailyHabit: DailyHabit)
    suspend fun getHabitsForPeriod(period: Int): List<DailyHabit>
    suspend fun remove(dailyHabit: DailyHabit)
    suspend fun removeNotCompletedByDescription(description: String)
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}