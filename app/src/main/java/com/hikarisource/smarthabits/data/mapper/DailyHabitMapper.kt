package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.data.entity.DailyHabitEntity
import com.hikarisource.smarthabits.domain.model.DailyHabit

interface DailyHabitMapper {

    fun toDomain(entity: DailyHabitEntity): DailyHabit

    fun fromDomain(domain: DailyHabit): DailyHabitEntity

    fun toDomainList(entityList: List<DailyHabitEntity>) = entityList.map { toDomain(it) }

    fun fromDomainList(domainList: List<DailyHabit>) = domainList.map { fromDomain(it) }
}
