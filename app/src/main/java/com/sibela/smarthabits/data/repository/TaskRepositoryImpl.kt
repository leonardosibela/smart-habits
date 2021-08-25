package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.TaskDao
import com.sibela.smarthabits.domain.model.Task
import com.sibela.smarthabits.domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val taskDao: TaskDao,
) : TaskRepository {

    override suspend fun save(task: Task) {
        taskDao.insert(task)
    }

    override suspend fun getAllTasksThatAreDaily() = taskDao.getAllThatAreDaily()

    override suspend fun getAllTasksThatAreWeekly() = taskDao.getAllThatAreWeekly()

    override suspend fun getAllTasksThatAreMonthly() = taskDao.getAllThatAreMonthly()

    override suspend fun getAllTasksThatAreYearly() = taskDao.getAllThatAreYearly()

    override suspend fun delete(task: Task) = taskDao.delete(task)

    override suspend fun edit(task: Task) = taskDao.update(task)
}