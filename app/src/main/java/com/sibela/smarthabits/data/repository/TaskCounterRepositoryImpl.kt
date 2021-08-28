package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.TaskCounterDao
import com.sibela.smarthabits.domain.model.TaskCounter
import com.sibela.smarthabits.domain.repository.TaskCounterRepository

class TaskCounterRepositoryImpl(
    private val taskCounterDao: TaskCounterDao
) : TaskCounterRepository {

    override suspend fun getLastDailyCounter() = taskCounterDao.getLastDailyTaskCounter()
    override suspend fun getLastWeeklyCounter() = taskCounterDao.getLastWeeklyTaskCounter()
    override suspend fun getLastMonthlyCounter() = taskCounterDao.getLastMonthlyTaskCounter()
    override suspend fun getLastYearlyCounter() = taskCounterDao.getLastYearlyTaskCounter()
    override suspend fun update(taskCounter: TaskCounter) = taskCounterDao.update(taskCounter)
}