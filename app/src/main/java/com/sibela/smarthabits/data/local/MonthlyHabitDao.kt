package com.sibela.smarthabits.data.local

import androidx.room.*
import com.sibela.smarthabits.data.entity.MonthlyHabitEntity
import com.sibela.smarthabits.domain.model.MonthlyHabit

@Dao
interface MonthlyHabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(monthlyHabit: MonthlyHabitEntity): Long

    @Update
    suspend fun update(monthlyHabit: MonthlyHabitEntity)

    @Delete
    suspend fun delete(monthlyHabit: MonthlyHabitEntity)

    @Query("SELECT * FROM monthlyHabits WHERE period = :period")
    suspend fun getHabitsForPeriod(period: Int): List<MonthlyHabitEntity>

    @Query("DELETE FROM monthlyHabits WHERE description = :description AND completed = 0")
    suspend fun deleteNotCompletedByDescription(description: String)

    @Query("UPDATE monthlyHabits SET description = :newDescription WHERE id = :id AND completed = 0")
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)

    @Query("UPDATE monthlyHabits SET description = :newDescription WHERE description = :oldDescription AND completed = 0")
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}