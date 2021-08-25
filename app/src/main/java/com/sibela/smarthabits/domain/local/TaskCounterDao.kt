package com.sibela.smarthabits.domain.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.sibela.smarthabits.domain.model.TaskCounter

@Dao
interface TaskCounterDao {

    @Update
    suspend fun update(taskCounter: TaskCounter)

    @Query("SELECT * FROM tasksCounter WHERE periodicity = 'DAILY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastDailyTaskCounter(): TaskCounter

    @Query("SELECT * FROM tasksCounter WHERE periodicity = 'WEEKLY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastWeeklyTaskCounter(): TaskCounter

    @Query("SELECT * FROM tasksCounter WHERE periodicity = 'MONTHLY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastMonthlyTaskCounter(): TaskCounter

    @Query("SELECT * FROM tasksCounter WHERE periodicity = 'YEARLY' ORDER BY period DESC LIMIT 1")
    suspend fun getLastYearlyTaskCounter(): TaskCounter
}