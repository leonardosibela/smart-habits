package com.sibela.smarthabits.domain.local

import androidx.room.*
import com.sibela.smarthabits.domain.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task): Long

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM tasks")
    suspend fun getAll(): List<Task>

    @Query("SELECT * FROM tasks WHERE periodicity = 'DAILY'")
    suspend fun getAllThatAreDaily(): List<Task>

    @Query("SELECT * FROM tasks WHERE periodicity = 'WEEKLY'")
    suspend fun getAllThatAreWeekly(): List<Task>

    @Query("SELECT * FROM tasks WHERE periodicity = 'MONTHLY'")
    suspend fun getAllThatAreMonthly(): List<Task>

    @Query("SELECT * FROM tasks WHERE periodicity = 'YEARLY'")
    suspend fun getAllThatAreYearly(): List<Task>
}