package com.sibela.smarthabits.data.local

import androidx.room.*
import com.sibela.smarthabits.data.entity.YearlyHabitEntity

@Dao
interface YearlyHabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(yearlyHabit: YearlyHabitEntity): Long

    @Update
    suspend fun update(yearlyHabit: YearlyHabitEntity)

    @Delete
    suspend fun delete(yearlyHabit: YearlyHabitEntity)

    @Query("SELECT * FROM yearlyHabits WHERE period = :period")
    suspend fun getHabitsForPeriod(period: Int): List<YearlyHabitEntity>

    @Query("DELETE FROM yearlyHabits WHERE description = :description AND completed = 0")
    suspend fun deleteNotCompletedByDescription(description: String)

    @Query("UPDATE yearlyHabits SET description = :newDescription WHERE id = :id AND completed = 0")
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)

    @Query("UPDATE yearlyHabits SET description = :newDescription WHERE description = :oldDescription AND completed = 0")
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}