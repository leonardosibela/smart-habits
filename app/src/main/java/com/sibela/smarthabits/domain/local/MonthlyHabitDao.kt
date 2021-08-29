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

    @Query("DELETE FROM monthlyHabits WHERE id = :id AND completed = 0")
    suspend fun deleteNotCompletedById(id: Int)
}