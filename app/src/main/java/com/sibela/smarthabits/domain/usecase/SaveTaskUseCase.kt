package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.Task
import com.sibela.smarthabits.domain.repository.TaskRepository

class SaveTaskUseCase(private val repository: TaskRepository) {

    internal suspend operator fun invoke(task: Task) {
        repository.save(task)
    }
}