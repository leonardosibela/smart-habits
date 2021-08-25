package com.sibela.smarthabits.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sibela.smarthabits.domain.model.*

@Database(
    entities = [
        Task::class, DailyTask::class, WeeklyTask::class, MonthlyTask::class,
        YearlyTask::class, TaskCounter::class
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun taskCounterDao(): TaskCounterDao
    abstract fun dailyTaskDao(): DailyTaskDao
    abstract fun weeklyTaskDao(): WeeklyTaskDao
    abstract fun monthlyTaskDao(): MonthlyTaskDao
    abstract fun yearlyTaskDao(): YearlyTaskDao

    companion object {
        const val DATABASE_NAME: String = "task_db"
    }
}