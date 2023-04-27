package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.data.entity.PeriodicityEntity
import com.hikarisource.smarthabits.domain.model.Periodicity

interface PeriodicityMapper {

    fun toDomain(periodicityEntity: PeriodicityEntity): Periodicity

    fun fromDomain(periodicity: Periodicity): PeriodicityEntity
}
