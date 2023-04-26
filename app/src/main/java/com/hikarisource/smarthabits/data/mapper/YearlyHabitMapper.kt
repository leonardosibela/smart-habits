package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.data.entity.YearlyHabitEntity
import com.hikarisource.smarthabits.domain.model.YearlyHabit

interface YearlyHabitMapper {
    fun toDomain(entity: YearlyHabitEntity): YearlyHabit

    fun fromDomain(domain: YearlyHabit): YearlyHabitEntity

    fun toDomainList(entityList: List<YearlyHabitEntity>) = entityList.map { toDomain(it) }

    fun fromDomainList(domainList: List<YearlyHabit>) = domainList.map { fromDomain(it) }
}
