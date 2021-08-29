package com.sibela.smarthabits.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "yearlyHabits")
data class YearlyHabit(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    override var id: Int = 0,

    @ColumnInfo(name = "description")
    override val description: String,

    @ColumnInfo(name = "completed")
    val completed: Boolean,

    @ColumnInfo(name = "period")
    var period: Int
) : PeriodicHabit