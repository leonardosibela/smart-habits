package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.data.entity.MonthlyHabitEntity
import com.sibela.smarthabits.domain.model.MonthlyHabit

interface MonthlyHabitMapper {
    fun toDomain(entity: MonthlyHabitEntity): MonthlyHabit

    fun fromDomain(domain: MonthlyHabit): MonthlyHabitEntity

    fun toDomainList(entityList: List<MonthlyHabitEntity>) = entityList.map { toDomain(it) }

    fun fromDomainList(domainList: List<MonthlyHabit>) = domainList.map { fromDomain(it) }
}