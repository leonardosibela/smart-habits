package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.model.Task

sealed class TaskResult {
    object Loading : TaskResult()
    object EmptyList : TaskResult()
    data class Success(val data: List<Task>) : TaskResult()
    data class Error(val data: Throwable) : TaskResult()
}