package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.resultBy
import com.sibela.smarthabits.domain.repository.TaskCounterRepository
import com.sibela.smarthabits.domain.repository.WeeklyTaskRepository

class GetCurrentWeeklyTasksUseCase(
    private val weeklyTaskRepository: WeeklyTaskRepository,
    private val taskCounterRepository: TaskCounterRepository
) {

    internal suspend operator fun invoke() = resultBy {
        val lastWeeklyCounter = taskCounterRepository.getLastWeeklyCounter()
        weeklyTaskRepository.getTaskForPeriod(lastWeeklyCounter.period)
    }
}