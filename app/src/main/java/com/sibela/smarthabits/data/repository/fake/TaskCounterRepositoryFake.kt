package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.TaskCounter
import com.sibela.smarthabits.domain.repository.TaskCounterRepository

class TaskCounterRepositoryFake : TaskCounterRepository {

    override suspend fun getLastDailyCounter() = TaskCounter(0, Periodicity.DAILY, 1)
    override suspend fun getLastWeeklyCounter() = TaskCounter(0, Periodicity.WEEKLY, 1)
    override suspend fun getLastMonthlyCounter() = TaskCounter(0, Periodicity.MONTHLY, 1)
    override suspend fun getLastYearlyCounter() = TaskCounter(0, Periodicity.YEARLY, 1)

    override suspend fun update(taskCounter: TaskCounter) {

    }
}