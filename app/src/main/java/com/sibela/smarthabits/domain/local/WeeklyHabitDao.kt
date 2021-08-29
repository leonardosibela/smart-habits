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

    @Query("DELETE FROM weeklyHabits WHERE id = :id AND completed = 0")
    suspend fun deleteNotCompletedById(id: Int)
}