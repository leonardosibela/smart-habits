package com.sibela.smarthabits.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dailyHabits")
class DailyHabit(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Int = 0,

    @ColumnInfo(name = "description")
    override val description: String,

    @ColumnInfo(name = "completed")
    val completed: Boolean,

    @ColumnInfo(name = "period")
    val period: Int
) : PeriodicHabit