package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.YearlyTask

interface YearlyTaskRepository {

    suspend fun save(yearlyTask: YearlyTask)
    suspend fun getTasksForPeriod(period: Int): List<YearlyTask>
    suspend fun remove(yearlyTask: YearlyTask)
}