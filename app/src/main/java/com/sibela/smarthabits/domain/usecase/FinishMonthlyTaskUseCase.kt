package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.MonthlyTask
import com.sibela.smarthabits.domain.repository.MonthlyTaskRepository

class FinishMonthlyTaskUseCase(private val monthlyTaskRepository: MonthlyTaskRepository) {

    internal suspend operator fun invoke(monthlyTask: MonthlyTask) {
        monthlyTaskRepository.remove(monthlyTask)
    }
}