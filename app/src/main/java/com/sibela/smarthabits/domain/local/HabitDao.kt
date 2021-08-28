package com.sibela.smarthabits.domain.local

import androidx.room.*
import com.sibela.smarthabits.domain.model.Habit

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habit: Habit): Long

    @Update
    suspend fun update(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)

    @Query("SELECT * FROM habits")
    suspend fun getAll(): List<Habit>

    @Query("SELECT * FROM habits WHERE periodicity = 'DAILY'")
    suspend fun getAllThatAreDaily(): List<Habit>

    @Query("SELECT * FROM habits WHERE periodicity = 'WEEKLY'")
    suspend fun getAllThatAreWeekly(): List<Habit>

    @Query("SELECT * FROM habits WHERE periodicity = 'MONTHLY'")
    suspend fun getAllThatAreMonthly(): List<Habit>

    @Query("SELECT * FROM habits WHERE periodicity = 'YEARLY'")
    suspend fun getAllThatAreYearly(): List<Habit>
}