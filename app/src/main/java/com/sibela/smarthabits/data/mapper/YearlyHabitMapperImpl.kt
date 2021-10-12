package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.data.entity.YearlyHabitEntity
import com.sibela.smarthabits.domain.model.YearlyHabit

class YearlyHabitMapperImpl : YearlyHabitMapper {
    override fun toDomain(entity: YearlyHabitEntity) = YearlyHabit(
        id = entity.id,
        description = entity.description,
        completed = entity.completed,
        period = entity.period
    )

    override fun fromDomain(domain: YearlyHabit) = YearlyHabitEntity(
        id = domain.id,
        description = domain.description,
        completed = domain.completed,
        period = domain.period
    )
}