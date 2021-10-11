package com.sibela.smarthabits.data.local

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

    @Query("DELETE FROM dailyHabits WHERE description = :description AND completed = 0")
    suspend fun deleteNotCompletedByDescription(description: String)

    @Query("UPDATE dailyHabits SET description = :newDescription WHERE id = :id AND completed = 0")
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)

    @Query("UPDATE dailyHabits SET description = :newDescription WHERE description = :oldDescription AND completed = 0")
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}