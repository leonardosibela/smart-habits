package com.sibela.smarthabits.data.local

import androidx.room.*
import com.sibela.smarthabits.domain.model.HabitCounter

@Dao
interface HabitCounterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habitCounter: HabitCounter)

    @Query("SELECT * FROM habitsCounter WHERE periodicity = 'DAILY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastDailyHabitCounter(): HabitCounter

    @Query("SELECT * FROM habitsCounter WHERE periodicity = 'WEEKLY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastWeeklyHabitCounter(): HabitCounter

    @Query("SELECT * FROM habitsCounter WHERE periodicity = 'MONTHLY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastMonthlyHabitCounter(): HabitCounter

    @Query("SELECT * FROM habitsCounter WHERE periodicity = 'YEARLY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastYearlyHabitCounter(): HabitCounter
}