package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.WeeklyTask
import com.sibela.smarthabits.domain.repository.WeeklyTaskRepository

class FinishWeeklyTaskUseCase(private val weeklyTaskRepository: WeeklyTaskRepository) {

    internal suspend operator fun invoke(weeklyTask: WeeklyTask) {
        weeklyTaskRepository.remove(weeklyTask)
    }
}