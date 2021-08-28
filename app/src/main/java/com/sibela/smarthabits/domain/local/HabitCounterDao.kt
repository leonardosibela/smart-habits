package com.sibela.smarthabits.domain.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.sibela.smarthabits.domain.model.HabitCounter

@Dao
interface HabitCounterDao {

    @Update
    suspend fun update(habitCounter: HabitCounter)

    @Query("SELECT * FROM habitsCounter WHERE periodicity = 'DAILY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastDailyHabitCounter(): HabitCounter

    @Query("SELECT * FROM habitsCounter WHERE periodicity = 'WEEKLY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastWeeklyHabitCounter(): HabitCounter

    @Query("SELECT * FROM habitsCounter WHERE periodicity = 'MONTHLY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastMonthlyHabitCounter(): HabitCounter

    @Query("SELECT * FROM habitsCounter WHERE periodicity = 'YEARLY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastYearlyHabitCounter(): HabitCounter
}