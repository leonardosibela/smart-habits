package com.hikarisource.smarthabits.data.repository

import androidx.annotation.VisibleForTesting
import com.hikarisource.smarthabits.data.entity.ScheduleDateEntity
import com.hikarisource.smarthabits.data.local.ScheduleDao
import com.hikarisource.smarthabits.domain.repository.ScheduleRepository
import java.time.LocalDateTime

class ScheduleRepositoryImpl(private val scheduleDao: ScheduleDao) : ScheduleRepository {

    override suspend fun getLastScheduleDate(): ScheduleDateEntity? {
        return scheduleDao.getAll().getOrNull(0)
    }

    override suspend fun setScheduleDate(dateTime: LocalDateTime) {
        dateTime.asScheduleEntity()?.also {
            scheduleDao.update(it)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun LocalDateTime?.asScheduleEntity() = this?.let {
        ScheduleDateEntity(
            year = year,
            month = monthValue,
            dayOfMonth = dayOfMonth,
            hour = hour,
            minute = minute,
            second = second,
            nanoSecond = nano
        )
    }
}
