package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.DailyHabit

interface DailyHabitRepository {

    suspend fun save(dailyHabit: DailyHabit)
    suspend fun getHabitFoPeriod(period: Int): List<DailyHabit>
    suspend fun remove(dailyHabit: DailyHabit)
}