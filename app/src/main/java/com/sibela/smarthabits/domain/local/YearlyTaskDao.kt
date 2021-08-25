package com.sibela.smarthabits.domain.local

import androidx.room.*
import com.sibela.smarthabits.domain.model.YearlyTask

@Dao
interface YearlyTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(yearlyTask: YearlyTask): Long

    @Update
    suspend fun update(yearlyTask: YearlyTask)

    @Delete
    suspend fun delete(yearlyTask: YearlyTask)

    @Query("SELECT * FROM yearlyTasks WHERE period = :period")
    suspend fun getTasksForPeriod(period: Int): List<YearlyTask>
}