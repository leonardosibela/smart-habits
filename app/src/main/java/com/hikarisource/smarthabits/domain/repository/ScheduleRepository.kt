package com.hikarisource.smarthabits.domain.repository

import com.hikarisource.smarthabits.data.entity.ScheduleDateEntity
import java.time.LocalDateTime

interface ScheduleRepository {
    suspend fun getLastScheduleDate(): ScheduleDateEntity?
    suspend fun setScheduleDate(dateTime: LocalDateTime)
}