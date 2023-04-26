package com.hikarisource.smarthabits.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hikarisource.smarthabits.data.entity.DailyHabitEntity
import com.hikarisource.smarthabits.data.entity.HabitCounterEntity
import com.hikarisource.smarthabits.data.entity.HabitEntity
import com.hikarisource.smarthabits.data.entity.MonthlyHabitEntity
import com.hikarisource.smarthabits.data.entity.ScheduleDateEntity
import com.hikarisource.smarthabits.data.entity.WeeklyHabitEntity
import com.hikarisource.smarthabits.data.entity.YearlyHabitEntity

@Database(
    entities = [
        HabitEntity::class, DailyHabitEntity::class, WeeklyHabitEntity::class,
        MonthlyHabitEntity::class, YearlyHabitEntity::class, HabitCounterEntity::class,
        ScheduleDateEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao
    abstract fun habitCounterDao(): HabitCounterDao
    abstract fun dailyHabitDao(): DailyHabitDao
    abstract fun weeklyHabitDao(): WeeklyHabitDao
    abstract fun monthlyHabitDao(): MonthlyHabitDao
    abstract fun yearlyHabitDao(): YearlyHabitDao
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        const val DATABASE_NAME: String = "habit_db"
    }
}
