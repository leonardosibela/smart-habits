package com.sibela.smarthabits.domain.local

import androidx.room.*
import com.sibela.smarthabits.domain.model.WeeklyHabit

@Dao
interface WeeklyHabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weeklyHabit: WeeklyHabit): Long

    @Update
    suspend fun update(weeklyHabit: WeeklyHabit)

    @Delete
    suspend fun delete(weeklyHabit: WeeklyHabit)

    @Query("SELECT * FROM weeklyHabits WHERE period = :period")
    suspend fun getHabitsForPeriod(period: Int): List<WeeklyHabit>

    @Query("DELETE FROM weeklyHabits WHERE description = :description AND completed = 0")
    suspend fun deleteNotCompletedByDescription(description: String)

    @Query("UPDATE weeklyHabits SET description = :newDescription WHERE id = :id AND completed = 0")
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)

    @Query("UPDATE weeklyHabits SET description = :newDescription WHERE description = :oldDescription AND completed = 0")
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}