package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.WeeklyTask

interface WeeklyTaskRepository {

    suspend fun save(weeklyTask: WeeklyTask)
    suspend fun getTaskForPeriod(period: Int): List<WeeklyTask>
    suspend fun remove(weeklyTask: WeeklyTask)
}