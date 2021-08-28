package com.sibela.smarthabits.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sibela.smarthabits.domain.model.*

@Database(
    entities = [
        Habit::class, DailyHabit::class, WeeklyHabit::class, MonthlyHabit::class,
        YearlyHabit::class, HabitCounter::class
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