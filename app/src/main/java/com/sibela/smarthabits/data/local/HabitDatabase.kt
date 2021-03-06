package com.sibela.smarthabits.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sibela.smarthabits.data.entity.*

@Database(
    entities = [
        HabitEntity::class, DailyHabitEntity::class, WeeklyHabitEntity::class,
        MonthlyHabitEntity::class, YearlyHabitEntity::class, HabitCounterEntity::class
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao
    abstract fun habitCounterDao(): HabitCounterDao
    abstract fun dailyHabitDao(): DailyHabitDao
    abstract fun weeklyHabitDao(): WeeklyHabitDao
    abstract fun monthlyHabitDao(): MonthlyHabitDao
    abstract fun yearlyHabitDao(): YearlyHabitDao

    companion object {
        const val DATABASE_NAME: String = "habit_db"
    }
}