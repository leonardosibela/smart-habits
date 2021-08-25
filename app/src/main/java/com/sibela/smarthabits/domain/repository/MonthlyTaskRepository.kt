package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.MonthlyTask

interface MonthlyTaskRepository {

    suspend fun save(monthlyTask: MonthlyTask)
    suspend fun getTaskForPeriod(period: Int): List<MonthlyTask>
}