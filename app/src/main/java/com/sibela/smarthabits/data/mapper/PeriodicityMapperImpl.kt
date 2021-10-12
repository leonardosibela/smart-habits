package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.data.entity.PeriodicityEntity
import com.sibela.smarthabits.domain.model.Periodicity

class PeriodicityMapperImpl : PeriodicityMapper {

    override fun toDomain(periodicityEntity: PeriodicityEntity): Periodicity {
        return when (periodicityEntity) {
            PeriodicityEntity.DAILY -> Periodicity.DAILY
            PeriodicityEntity.WEEKLY -> Periodicity.WEEKLY
            PeriodicityEntity.MONTHLY -> Periodicity.MONTHLY
            PeriodicityEntity.YEARLY -> Periodicity.YEARLY
        }
    }

    override fun fromDomain(periodicity: Periodicity): PeriodicityEntity {
        return when (periodicity) {
            Periodicity.DAILY -> PeriodicityEntity.DAILY
            Periodicity.WEEKLY -> PeriodicityEntity.WEEKLY
            Periodicity.MONTHLY -> PeriodicityEntity.MONTHLY
            Periodicity.YEARLY -> PeriodicityEntity.YEARLY
        }
    }
}