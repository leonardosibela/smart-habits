package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.data.entity.HabitCounterEntity
import com.sibela.smarthabits.domain.model.HabitCounter

interface HabitCounterMapper {

    fun toDomain(entity: HabitCounterEntity): HabitCounter

    fun fromDomain(domain: HabitCounter): HabitCounterEntity

    fun toDomainList(entityList: List<HabitCounterEntity>) = entityList.map { toDomain(it) }

    fun fromDomainList(domainList: List<HabitCounter>) = domainList.map { fromDomain(it) }
}