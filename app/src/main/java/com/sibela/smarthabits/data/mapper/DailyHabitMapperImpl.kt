package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.data.entity.DailyHabitEntity
import com.sibela.smarthabits.domain.model.DailyHabit

class DailyHabitMapperImpl : DailyHabitMapper {

    override fun toDomain(entity: DailyHabitEntity) = DailyHabit(
        id = entity.id,
        description = entity.description,
        completed = entity.completed,
        period = entity.period
    )

    override fun fromDomain(domain: DailyHabit) = DailyHabitEntity(
        id = domain.id,
        description = domain.description,
        completed = domain.completed,
        period = domain.period
    )
}