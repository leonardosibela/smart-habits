package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.resultBy
import com.sibela.smarthabits.domain.repository.DailyTaskRepository
import com.sibela.smarthabits.domain.repository.TaskCounterRepository

class GetCurrentDailyTasksUseCase(
    private val dailyTaskRepository: DailyTaskRepository,
    private val taskCounterRepository: TaskCounterRepository
) {

    internal suspend operator fun invoke() = resultBy {
        val lastDailyCounter = taskCounterRepository.getLastDailyCounter()
        dailyTaskRepository.getTaskFoPeriod(lastDailyCounter.period)
    }
}