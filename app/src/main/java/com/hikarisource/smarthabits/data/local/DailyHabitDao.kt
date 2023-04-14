package com.hikarisource.smarthabits.data.local

import androidx.room.*
import com.hikarisource.smarthabits.data.entity.DailyHabitEntity

@Dao
interface DailyHabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dailyHabit: DailyHabitEntity): Long

    @Update
    suspend fun update(dailyHabit: DailyHabitEntity)

    @Delete
    suspend fun delete(dailyHabit: DailyHabitEntity)

    @Query("SELECT * FROM dailyHabits WHERE period = :period")
    suspend fun getHabitsForPeriod(period: Int): List<DailyHabitEntity>

    @Query("DELETE FROM dailyHabits WHERE description = :description AND completed = 0")
    suspend fun deleteNotCompletedByDescription(description: String)

    @Query("UPDATE dailyHabits SET description = :newDescription WHERE id = :id AND completed = 0")
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)

    @Query("UPDATE dailyHabits SET description = :newDescription WHERE description = :oldDescription AND completed = 0")
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}