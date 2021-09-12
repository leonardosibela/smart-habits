package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.YearlyHabit

interface YearlyHabitRepository {

    suspend fun save(yearlyHabit: YearlyHabit)
    suspend fun getHabitsForPeriod(period: Int): List<YearlyHabit>
    suspend fun remove(yearlyHabit: YearlyHabit)
    suspend fun removeNotCompletedByDescription(description: String)
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}