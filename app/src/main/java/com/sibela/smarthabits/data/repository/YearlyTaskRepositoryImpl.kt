package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.YearlyTaskDao
import com.sibela.smarthabits.domain.model.YearlyTask
import com.sibela.smarthabits.domain.repository.YearlyTaskRepository

class YearlyTaskRepositoryImpl(
    private val yearlyTaskDao: YearlyTaskDao
) : YearlyTaskRepository {

    override suspend fun save(yearlyTask: YearlyTask) {
        yearlyTaskDao.insert(yearlyTask)
    }

    override suspend fun getTasksForPeriod(period: Int) = yearlyTaskDao.getTasksForPeriod(period)
}