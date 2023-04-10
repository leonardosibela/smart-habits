package com.sibela.smarthabits.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scheduleDate")
data class ScheduleDateEntity(
    @ColumnInfo(name = "year")
    val year: Int,

    @ColumnInfo(name = "month")
    val month: Int,

    @ColumnInfo(name = "day")
    val dayOfMonth: Int,

    @ColumnInfo(name = "hour")
    val hour: Int,

    @ColumnInfo(name = "minute")
    val minute: Int,

    @ColumnInfo(name = "second")
    val second: Int,

    @ColumnInfo(name = "nanoSecond")
    val nanoSecond: Int,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 1
)