package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.MonthlyHabit

interface MonthlyHabitRepository {

    suspend fun save(monthlyHabit: MonthlyHabit)
    suspend fun getHabitsForPeriod(period: Int): List<MonthlyHabit>
    suspend fun remove(monthlyHabit: MonthlyHabit)
    suspend fun removeNotCompletedByDescription(description: String)
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}