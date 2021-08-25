package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.DailyTask
import com.sibela.smarthabits.domain.repository.DailyTaskRepository

class FinishDailyTaskUseCase(private val dailyTaskRepository: DailyTaskRepository) {

    internal suspend operator fun invoke(dailyTask: DailyTask) {
        dailyTaskRepository.remove(dailyTask)
    }
}