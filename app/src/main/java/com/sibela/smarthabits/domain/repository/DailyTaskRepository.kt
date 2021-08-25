package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.DailyTask

interface DailyTaskRepository {

    suspend fun save(dailyTask: DailyTask)
    suspend fun getTaskFoPeriod(period: Int): List<DailyTask>
}