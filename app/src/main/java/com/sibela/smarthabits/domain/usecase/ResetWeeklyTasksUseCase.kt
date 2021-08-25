package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.TaskMapper
import com.sibela.smarthabits.domain.repository.TaskCounterRepository
import com.sibela.smarthabits.domain.repository.TaskRepository
import com.sibela.smarthabits.domain.repository.WeeklyTaskRepository

class ResetWeeklyTasksUseCase(
    private val taskMapper: TaskMapper,
    private val taskRepository: TaskRepository,
    private val weeklyTaskRepository: WeeklyTaskRepository,
    private val taskCounterRepository: TaskCounterRepository
) {

    internal suspend operator fun invoke() {
        val weeklyCounter = taskCounterRepository.getLastWeeklyCounter()
        weeklyCounter.period = weeklyCounter.period++
        taskCounterRepository.update(weeklyCounter)
        val tasks = taskRepository.getAllTasksThatAreWeekly()
        val weeklyTasks = taskMapper.toWeeklyTasks(tasks, false, weeklyCounter.period)
        weeklyTasks.forEach { weeklyTask -> weeklyTaskRepository.save(weeklyTask) }
    }
}