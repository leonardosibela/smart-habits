package com.sibela.smarthabits.domain.local

import androidx.room.*
import com.sibela.smarthabits.domain.model.WeeklyTask

@Dao
interface WeeklyTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weeklyTask: WeeklyTask): Long

    @Update
    suspend fun update(weeklyTask: WeeklyTask)

    @Delete
    suspend fun delete(weeklyTask: WeeklyTask)

    @Query("SELECT * FROM weeklyTasks WHERE period = :period")
    suspend fun getTaskForPeriod(period: Int): List<WeeklyTask>
}