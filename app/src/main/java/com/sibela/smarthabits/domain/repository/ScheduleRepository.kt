package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.data.entity.ScheduleDateEntity
import java.time.LocalDateTime

interface ScheduleRepository {
    suspend fun getLastScheduleDate(): ScheduleDateEntity?
    suspend fun setScheduleDate(dateTime: LocalDateTime)
}