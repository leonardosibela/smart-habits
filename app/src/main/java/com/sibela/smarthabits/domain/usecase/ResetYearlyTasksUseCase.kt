package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.TaskMapper
import com.sibela.smarthabits.domain.repository.TaskCounterRepository
import com.sibela.smarthabits.domain.repository.TaskRepository
import com.sibela.smarthabits.domain.repository.YearlyTaskRepository

class ResetYearlyTasksUseCase(
    private val taskMapper: TaskMapper,
    private val taskRepository: TaskRepository,
    private val yearlyTaskRepository: YearlyTaskRepository,
    private val taskCounterRepository: TaskCounterRepository
) {

    internal suspend operator fun invoke() {
        val yearlyCounter = taskCounterRepository.getLastYearlyCounter()
        yearlyCounter.period = yearlyCounter.period++
        taskCounterRepository.update(yearlyCounter)
        val tasks = taskRepository.getAllTasksThatAreYearly()
        val yearlyTasks = taskMapper.toYearlyTasks(tasks, false, yearlyCounter.period)
        yearlyTasks.forEach { yearlyTask -> yearlyTaskRepository.save(yearlyTask) }
    }
}