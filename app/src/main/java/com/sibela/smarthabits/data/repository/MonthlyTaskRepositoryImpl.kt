package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.MonthlyTaskDao
import com.sibela.smarthabits.domain.model.MonthlyTask
import com.sibela.smarthabits.domain.repository.MonthlyTaskRepository

class MonthlyTaskRepositoryImpl(
    private val monthlyTaskDao: MonthlyTaskDao
) : MonthlyTaskRepository {


    override suspend fun save(monthlyTask: MonthlyTask) {
        monthlyTaskDao.insert(monthlyTask)
    }

    override suspend fun getTaskForPeriod(period: Int) = monthlyTaskDao.getTaskForPeriod(period)

    override suspend fun remove(monthlyTask: MonthlyTask) = monthlyTaskDao.delete(monthlyTask)
}