package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.data.entity.HabitEntity
import com.sibela.smarthabits.domain.model.Habit

interface HabitMapper {
    fun toDomain(entity: HabitEntity): Habit

    fun fromDomain(domain: Habit): HabitEntity

    fun toDomainList(entityList: List<HabitEntity>) = entityList.map { toDomain(it) }

    fun fromDomainList(domainList: List<Habit>) = domainList.map { fromDomain(it) }
}