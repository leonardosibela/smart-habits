package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.WeeklyHabit

interface WeeklyHabitRepository {

    suspend fun save(weeklyHabit: WeeklyHabit)
    suspend fun getHabitsForPeriod(period: Int): List<WeeklyHabit>
    suspend fun remove(weeklyHabit: WeeklyHabit)
    suspend fun removeNotCompletedByDescription(description: String)
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}