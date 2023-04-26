package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.data.entity.HabitCounterEntity
import com.hikarisource.smarthabits.domain.model.HabitCounter

interface HabitCounterMapper {

    fun toDomain(entity: HabitCounterEntity): HabitCounter

    fun fromDomain(domain: HabitCounter): HabitCounterEntity

    fun toDomainList(entityList: List<HabitCounterEntity>) = entityList.map { toDomain(it) }

    fun fromDomainList(domainList: List<HabitCounter>) = domainList.map { fromDomain(it) }
}
