package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.data.entity.HabitCounterEntity
import com.hikarisource.smarthabits.domain.model.HabitCounter

class HabitCounterMapperImpl(private val periodicityMapper: PeriodicityMapper) :
    HabitCounterMapper {

    override fun toDomain(entity: HabitCounterEntity) = HabitCounter(
        id = entity.id,
        periodicity = periodicityMapper.toDomain(entity.periodicity),
        period = entity.period
    )

    override fun fromDomain(domain: HabitCounter) = HabitCounterEntity(
        id = domain.id,
        periodicity = periodicityMapper.fromDomain(domain.periodicity),
        period = domain.period
    )
}
