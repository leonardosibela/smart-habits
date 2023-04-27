package com.hikarisource.smarthabits.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habitsCounter")
data class HabitCounterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "periodicity")
    val periodicity: PeriodicityEntity,

    @ColumnInfo(name = "period")
    var period: Int
)
