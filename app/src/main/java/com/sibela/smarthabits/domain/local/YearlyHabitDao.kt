package com.sibela.smarthabits.domain.local

import androidx.room.*
import com.sibela.smarthabits.domain.model.YearlyHabit

@Dao
interface YearlyHabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(yearlyHabit: YearlyHabit): Long

    @Update
    suspend fun update(yearlyHabit: YearlyHabit)

    @Delete
    suspend fun delete(yearlyHabit: YearlyHabit)

    @Query("SELECT * FROM yearlyHabits WHERE period = :period")
    suspend fun getHabitsForPeriod(period: Int): List<YearlyHabit>
}