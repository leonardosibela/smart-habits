package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.data.entity.WeeklyHabitEntity
import com.sibela.smarthabits.domain.model.WeeklyHabit

interface WeeklyHabitMapper {
    fun toDomain(entity: WeeklyHabitEntity): WeeklyHabit

    fun fromDomain(domain: WeeklyHabit): WeeklyHabitEntity

    fun toDomainList(entityList: List<WeeklyHabitEntity>) = entityList.map { toDomain(it) }

    fun fromDomainList(domainList: List<WeeklyHabit>) = domainList.map { fromDomain(it) }
}