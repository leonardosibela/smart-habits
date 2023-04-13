package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.HabitCounter

interface HabitCounterRepository {

    suspend fun getLastDailyCounter(): HabitCounter
    suspend fun getLastWeeklyCounter(): HabitCounter
    suspend fun getLastMonthlyCounter(): HabitCounter
    suspend fun getLastYearlyCounter(): HabitCounter
    suspend fun insert(habitCounter: HabitCounter)
}