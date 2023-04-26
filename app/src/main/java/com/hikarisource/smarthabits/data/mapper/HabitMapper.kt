package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.data.entity.HabitEntity
import com.hikarisource.smarthabits.domain.model.Habit

interface HabitMapper {
    fun toDomain(entity: HabitEntity): Habit

    fun fromDomain(domain: Habit): HabitEntity

    fun toDomainList(entityList: List<HabitEntity>) = entityList.map { toDomain(it) }

    fun fromDomainList(domainList: List<Habit>) = domainList.map { fromDomain(it) }
}
