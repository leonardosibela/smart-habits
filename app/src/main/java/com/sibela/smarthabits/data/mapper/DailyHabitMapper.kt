package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.data.entity.DailyHabitEntity
import com.sibela.smarthabits.domain.model.DailyHabit

interface DailyHabitMapper {

    fun toDomain(entity: DailyHabitEntity): DailyHabit

    fun fromDomain(domain: DailyHabit): DailyHabitEntity

    fun toDomainList(entityList: List<DailyHabitEntity>) = entityList.map { toDomain(it) }

    fun fromDomainList(domainList: List<DailyHabit>) = domainList.map { fromDomain(it) }
}