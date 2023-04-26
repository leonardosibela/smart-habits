package com.hikarisource.smarthabits.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hikarisource.smarthabits.data.entity.WeeklyHabitEntity

@Dao
interface WeeklyHabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weeklyHabit: WeeklyHabitEntity): Long

    @Update
    suspend fun update(weeklyHabit: WeeklyHabitEntity)

    @Delete
    suspend fun delete(weeklyHabit: WeeklyHabitEntity)

    @Query("SELECT * FROM weeklyHabits WHERE period = :period")
    suspend fun getHabitsForPeriod(period: Int): List<WeeklyHabitEntity>

    @Query("DELETE FROM weeklyHabits WHERE description = :description AND completed = 0")
    suspend fun deleteNotCompletedByDescription(description: String)

    @Query("UPDATE weeklyHabits SET description = :newDescription WHERE id = :id AND completed = 0")
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)

    @Query("UPDATE weeklyHabits SET description = :newDescription WHERE description = :oldDescription AND completed = 0")
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}