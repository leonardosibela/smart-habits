package com.hikarisource.smarthabits.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hikarisource.smarthabits.data.entity.MonthlyHabitEntity

@Dao
@Suppress("MaxLineLength")
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

    @Query(
        "UPDATE monthlyHabits SET description = :newDescription WHERE id = :id AND completed = 0"
    )
    suspend fun updateNotCompletedDescription(id: Int, newDescription: String)

    @Query(
        "UPDATE monthlyHabits " +
            "SET description = :newDescription " +
            "WHERE description = :oldDescription AND completed = 0"
    )
    suspend fun updateNotCompletedDescription(oldDescription: String, newDescription: String)
}
