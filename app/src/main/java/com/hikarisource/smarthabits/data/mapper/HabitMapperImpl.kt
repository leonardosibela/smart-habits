package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.data.entity.HabitEntity
import com.hikarisource.smarthabits.domain.model.Habit

class HabitMapperImpl(private val periodicityMapper: PeriodicityMapper) : HabitMapper {
    override fun toDomain(entity: HabitEntity) = Habit(
        id = entity.id,
        description = entity.description,
        periodicity = periodicityMapper.toDomain(entity.periodicity)
    )

    override fun fromDomain(domain: Habit) = HabitEntity(
        id = domain.id,
        description = domain.description,
        periodicity = periodicityMapper.fromDomain(domain.periodicity)
    )
}
