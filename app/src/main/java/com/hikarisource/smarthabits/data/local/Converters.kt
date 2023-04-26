package com.hikarisource.smarthabits.data.local

import androidx.room.TypeConverter
import com.hikarisource.smarthabits.data.entity.PeriodicityEntity

class Converters {

    @TypeConverter
    fun toPeriodicity(value: String) = enumValueOf<PeriodicityEntity>(value)

    @TypeConverter
    fun fromPeriodicity(value: PeriodicityEntity) = value.name
}
