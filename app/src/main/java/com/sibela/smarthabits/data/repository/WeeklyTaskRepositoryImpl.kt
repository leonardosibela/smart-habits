package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.WeeklyTaskDao
import com.sibela.smarthabits.domain.model.WeeklyTask
import com.sibela.smarthabits.domain.repository.WeeklyTaskRepository

class WeeklyTaskRepositoryImpl(
    private val weeklyTaskDao: WeeklyTaskDao
) : WeeklyTaskRepository {

    override suspend fun save(weeklyTask: WeeklyTask) {
        weeklyTaskDao.insert(weeklyTask)
    }

    override suspend fun getTaskForPeriod(period: Int) = weeklyTaskDao.getTaskForPeriod(period)
}