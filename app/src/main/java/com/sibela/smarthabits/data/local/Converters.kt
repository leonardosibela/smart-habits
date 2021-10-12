package com.sibela.smarthabits.data.local

import androidx.room.TypeConverter
import com.sibela.smarthabits.data.entity.PeriodicityEntity
import com.sibela.smarthabits.domain.model.Periodicity

class Converters {

    @TypeConverter
    fun toPeriodicity(value: String) = enumValueOf<PeriodicityEntity>(value)

    @TypeConverter
    fun fromPeriodicity(value: PeriodicityEntity) = value.name
}