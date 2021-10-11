package com.sibela.smarthabits.data.local

import androidx.room.TypeConverter
import com.sibela.smarthabits.domain.model.Periodicity

class Converters {

    @TypeConverter
    fun toPeriodicity(value: String) = enumValueOf<Periodicity>(value)

    @TypeConverter
    fun fromPeriodicity(value: Periodicity) = value.name
}