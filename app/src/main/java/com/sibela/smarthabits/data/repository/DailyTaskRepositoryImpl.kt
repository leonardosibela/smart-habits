package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.DailyTaskDao
import com.sibela.smarthabits.domain.model.DailyTask
import com.sibela.smarthabits.domain.repository.DailyTaskRepository

class DailyTaskRepositoryImpl(
    private val dailyTaskDao: DailyTaskDao,
) : DailyTaskRepository {

    override suspend fun save(dailyTask: DailyTask) {
        dailyTaskDao.insert(dailyTask)
    }

    override suspend fun getTaskFoPeriod(period: Int) = dailyTaskDao.getTaskForPeriod(period)
}