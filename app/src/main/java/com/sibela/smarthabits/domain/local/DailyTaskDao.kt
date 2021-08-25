package com.sibela.smarthabits.domain.local

import androidx.room.*
import com.sibela.smarthabits.domain.model.DailyTask

@Dao
interface DailyTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dailyTask: DailyTask): Long

    @Update
    suspend fun update(dailyTask: DailyTask)

    @Delete
    suspend fun delete(dailyTask: DailyTask)

    @Query("SELECT * FROM dailyTasks WHERE period = :period")
    suspend fun getTaskForPeriod(period: Int): List<DailyTask>
}