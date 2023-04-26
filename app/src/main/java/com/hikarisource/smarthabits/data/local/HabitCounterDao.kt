package com.hikarisource.smarthabits.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hikarisource.smarthabits.data.entity.HabitCounterEntity

@Dao
interface HabitCounterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habitCounter: HabitCounterEntity)

    @Query("SELECT * FROM habitsCounter WHERE periodicity = 'DAILY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastDailyHabitCounter(): HabitCounterEntity

    @Query("SELECT * FROM habitsCounter WHERE periodicity = 'WEEKLY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastWeeklyHabitCounter(): HabitCounterEntity

    @Query("SELECT * FROM habitsCounter WHERE periodicity = 'MONTHLY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastMonthlyHabitCounter(): HabitCounterEntity

    @Query("SELECT * FROM habitsCounter WHERE periodicity = 'YEARLY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastYearlyHabitCounter(): HabitCounterEntity
}