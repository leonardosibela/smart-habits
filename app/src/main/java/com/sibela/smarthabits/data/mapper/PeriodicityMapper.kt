package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.data.entity.PeriodicityEntity
import com.sibela.smarthabits.domain.model.Periodicity

interface PeriodicityMapper {

    fun toDomain(periodicityEntity: PeriodicityEntity): Periodicity

    fun fromDomain(periodicity: Periodicity): PeriodicityEntity

}