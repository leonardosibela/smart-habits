package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.MonthlyHabit

interface MonthlyHabitRepository {

    suspend fun save(monthlyHabit: MonthlyHabit)
    suspend fun getHabitForPeriod(period: Int): List<MonthlyHabit>
    suspend fun remove(monthlyHabit: MonthlyHabit)
    suspend fun removeNotCompletedById(id: Int)
}