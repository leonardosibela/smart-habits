package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.*

interface TaskCounterRepository {

    suspend fun getLastDailyCounter(): TaskCounter
    suspend fun getLastWeeklyCounter(): TaskCounter
    suspend fun getLastMonthlyCounter(): TaskCounter
    suspend fun getLastYearlyCounter(): TaskCounter
    suspend fun update(taskCounter: TaskCounter)
}