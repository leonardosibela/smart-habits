package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.data.entity.WeeklyHabitEntity
import com.sibela.smarthabits.domain.model.WeeklyHabit

class WeeklyHabitMapperImpl : WeeklyHabitMapper {
    override fun toDomain(entity: WeeklyHabitEntity) = WeeklyHabit(
        id = entity.id,
        description = entity.description,
        completed = entity.completed,
        period = entity.period
    )

    override fun fromDomain(domain: WeeklyHabit) = WeeklyHabitEntity(
        id = domain.id,
        description = domain.description,
        completed = domain.completed,
        period = domain.period
    )
}