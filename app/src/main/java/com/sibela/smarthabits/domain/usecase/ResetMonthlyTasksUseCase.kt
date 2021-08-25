package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.TaskMapper
import com.sibela.smarthabits.domain.repository.MonthlyTaskRepository
import com.sibela.smarthabits.domain.repository.TaskCounterRepository
import com.sibela.smarthabits.domain.repository.TaskRepository

class ResetMonthlyTasksUseCase(
    private val taskMapper: TaskMapper,
    private val taskRepository: TaskRepository,
    private val monthlyTaskRepository: MonthlyTaskRepository,
    private val taskCounterRepository: TaskCounterRepository
) {

    internal suspend operator fun invoke() {
        val monthlyCounter = taskCounterRepository.getLastMonthlyCounter()
        monthlyCounter.period = monthlyCounter.period++
        taskCounterRepository.update(monthlyCounter)
        val tasks = taskRepository.getAllTasksThatAreMonthly()
        val monthlyTasks = taskMapper.toMonthlyTasks(tasks, false, monthlyCounter.period)
        monthlyTasks.forEach { monthlyTask -> monthlyTaskRepository.save(monthlyTask) }
    }
}