package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.data.entity.MonthlyHabitEntity
import com.hikarisource.smarthabits.domain.model.MonthlyHabit

class MonthlyHabitMapperImpl : MonthlyHabitMapper {
    override fun toDomain(entity: MonthlyHabitEntity) = MonthlyHabit(
        id = entity.id,
        description = entity.description,
        completed = entity.completed,
        period = entity.period
    )

    override fun fromDomain(domain: MonthlyHabit) = MonthlyHabitEntity(
        id = domain.id,
        description = domain.description,
        completed = domain.completed,
        period = domain.period
    )
}
