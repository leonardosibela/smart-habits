package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.YearlyTask
import com.sibela.smarthabits.domain.repository.YearlyTaskRepository

class FinishYearlyTaskUseCase(private val yearlyTaskRepository: YearlyTaskRepository) {

    internal suspend operator fun invoke(yearlyTask: YearlyTask) {
        yearlyTaskRepository.remove(yearlyTask)
    }
}