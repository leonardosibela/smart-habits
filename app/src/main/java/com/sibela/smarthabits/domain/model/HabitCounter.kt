package com.sibela.smarthabits.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habitsCounter")
data class HabitCounter(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "periodicity")
    val periodicity: Periodicity,

    @ColumnInfo(name = "period")
    var period: Int,
)