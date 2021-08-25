package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.resultBy
import com.sibela.smarthabits.domain.repository.TaskCounterRepository
import com.sibela.smarthabits.domain.repository.YearlyTaskRepository

class GetCurrentYearlyTasksUseCase(
    private val yearlyTaskRepository: YearlyTaskRepository,
    private val taskCounterRepository: TaskCounterRepository
) {

    internal suspend operator fun invoke() = resultBy {
        val lastYearlyCounter = taskCounterRepository.getLastYearlyCounter()
        yearlyTaskRepository.getTasksForPeriod(lastYearlyCounter.period)
    }
}