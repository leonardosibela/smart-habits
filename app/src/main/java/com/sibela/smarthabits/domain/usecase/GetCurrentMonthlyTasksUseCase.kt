package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.resultBy
import com.sibela.smarthabits.domain.repository.MonthlyTaskRepository
import com.sibela.smarthabits.domain.repository.TaskCounterRepository

class GetCurrentMonthlyTasksUseCase(
    private val monthlyTaskRepository: MonthlyTaskRepository,
    private val taskCounterRepository: TaskCounterRepository
) {

    internal suspend operator fun invoke() = resultBy {
        val lastMonthlyCounter = taskCounterRepository.getLastMonthlyCounter()
        monthlyTaskRepository.getTaskForPeriod(lastMonthlyCounter.period)
    }
}