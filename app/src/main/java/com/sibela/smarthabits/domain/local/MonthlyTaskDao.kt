package com.sibela.smarthabits.domain.local

import androidx.room.*
import com.sibela.smarthabits.domain.model.MonthlyTask

@Dao
interface MonthlyTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(monthlyTask: MonthlyTask): Long

    @Update
    suspend fun update(monthlyTask: MonthlyTask)

    @Delete
    suspend fun delete(monthlyTask: MonthlyTask)

    @Query("SELECT * FROM monthlyTasks WHERE period = :period")
    suspend fun getTaskForPeriod(period: Int): List<MonthlyTask>
}