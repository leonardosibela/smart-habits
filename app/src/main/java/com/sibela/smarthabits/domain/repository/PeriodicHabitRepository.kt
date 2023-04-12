package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.PeriodicHabit

interface PeriodicHabitRepository<T : PeriodicHabit> {

    suspend fun save(habit: T)
    suspend fun getHabitsForLastPeriod(): List<T>
    suspend fun remove(habit: T)
    suspend fun removeNotCompletedByDescription(description: String)
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}