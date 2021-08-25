package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.Task
import com.sibela.smarthabits.domain.repository.TaskRepository

class EditTaskUseCase(private val taskRepository: TaskRepository) {

    internal suspend operator fun invoke(task: Task) {
        taskRepository.edit(task)
    }
}