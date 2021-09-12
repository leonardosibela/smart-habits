package com.sibela.smarthabits.domain.local

import androidx.room.*
import com.sibela.smarthabits.domain.model.MonthlyHabit

@Dao
interface MonthlyHabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(monthlyHabit: MonthlyHabit): Long

    @Update
    suspend fun update(monthlyHabit: MonthlyHabit)

    @Delete
    suspend fun delete(monthlyHabit: MonthlyHabit)

    @Query("SELECT * FROM monthlyHabits WHERE period = :period")
    suspend fun getHabitsForPeriod(period: Int): List<MonthlyHabit>

    @Query("DELETE FROM monthlyHabits WHERE description = :description AND completed = 0")
    suspend fun deleteNotCompletedByDescription(description: String)

    @Query("UPDATE monthlyHabits SET description = :newDescription WHERE id = :id AND completed = 0")
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)

    @Query("UPDATE monthlyHabits SET description = :newDescription WHERE description = :oldDescription AND completed = 0")
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}