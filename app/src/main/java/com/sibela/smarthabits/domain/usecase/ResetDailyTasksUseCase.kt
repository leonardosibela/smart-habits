package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.TaskMapper
import com.sibela.smarthabits.domain.repository.DailyTaskRepository
import com.sibela.smarthabits.domain.repository.TaskCounterRepository
import com.sibela.smarthabits.domain.repository.TaskRepository

class ResetDailyTasksUseCase(
    private val taskMapper: TaskMapper,
    private val taskRepository: TaskRepository,
    private val dailyTaskRepository: DailyTaskRepository,
    private val taskCounterRepository: TaskCounterRepository
) {

    internal suspend operator fun invoke() {
        val dailyCounter = taskCounterRepository.getLastDailyCounter()
        dailyCounter.period = dailyCounter.period++
        taskCounterRepository.update(dailyCounter)
        val tasks = taskRepository.getAllTasksThatAreDaily()
        val dailyTasks = taskMapper.toDailyTasks(tasks, false, dailyCounter.period)
        dailyTasks.forEach { dailyTask -> dailyTaskRepository.save(dailyTask) }
    }
}