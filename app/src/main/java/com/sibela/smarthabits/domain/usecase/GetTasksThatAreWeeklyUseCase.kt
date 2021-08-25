package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.resultBy
import com.sibela.smarthabits.domain.repository.TaskRepository

class GetTasksThatAreWeeklyUseCase(private val taskRepository: TaskRepository) {

    internal suspend operator fun invoke() = resultBy {
        taskRepository.getAllTasksThatAreWeekly()
    }
}