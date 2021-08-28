package com.sibela.smarthabits.domain.local

import androidx.room.*
import com.sibela.smarthabits.domain.model.DailyHabit

@Dao
interface DailyHabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dailyHabit: DailyHabit): Long

    @Update
    suspend fun update(dailyHabit: DailyHabit)

    @Delete
    suspend fun delete(dailyHabit: DailyHabit)

    @Query("SELECT * FROM dailyHabits WHERE period = :period")
    suspend fun getHabitsForPeriod(period: Int): List<DailyHabit>
}