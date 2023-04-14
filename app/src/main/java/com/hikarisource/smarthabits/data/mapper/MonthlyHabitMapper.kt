package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.data.entity.MonthlyHabitEntity
import com.hikarisource.smarthabits.domain.model.MonthlyHabit

interface MonthlyHabitMapper {
    fun toDomain(entity: MonthlyHabitEntity): MonthlyHabit

    fun fromDomain(domain: MonthlyHabit): MonthlyHabitEntity

    fun toDomainList(entityList: List<MonthlyHabitEntity>) = entityList.map { toDomain(it) }

    fun fromDomainList(domainList: List<MonthlyHabit>) = domainList.map { fromDomain(it) }
}