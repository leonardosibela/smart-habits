package com.hikarisource.smarthabits.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hikarisource.smarthabits.data.entity.HabitEntity

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habit: HabitEntity): Long

    @Query("UPDATE habits SET description = :newDescription WHERE id = :id")
    suspend fun updateDescription(id: Int, newDescription: String)

    @Delete
    suspend fun delete(habit: HabitEntity)

    @Query("SELECT * FROM habits WHERE periodicity = 'DAILY'")
    suspend fun getAllThatAreDaily(): List<HabitEntity>

    @Query("SELECT * FROM habits WHERE periodicity = 'WEEKLY'")
    suspend fun getAllThatAreWeekly(): List<HabitEntity>

    @Query("SELECT * FROM habits WHERE periodicity = 'MONTHLY'")
    suspend fun getAllThatAreMonthly(): List<HabitEntity>

    @Query("SELECT * FROM habits WHERE periodicity = 'YEARLY'")
    suspend fun getAllThatAreYearly(): List<HabitEntity>
}