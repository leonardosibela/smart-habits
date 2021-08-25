package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.Task

interface TaskRepository {

    suspend fun save(task: Task)
    suspend fun getAllTasksThatAreDaily(): List<Task>
    suspend fun getAllTasksThatAreWeekly(): List<Task>
    suspend fun getAllTasksThatAreMonthly(): List<Task>
    suspend fun getAllTasksThatAreYearly(): List<Task>
    suspend fun delete(task: Task)
    suspend fun edit(task: Task)
}