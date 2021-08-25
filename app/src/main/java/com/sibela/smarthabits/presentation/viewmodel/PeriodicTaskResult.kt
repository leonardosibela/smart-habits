package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.model.PeriodicTask

sealed class PeriodicTaskResult<T : PeriodicTask> {
    class Loading<T : PeriodicTask> : PeriodicTaskResult<T>()
    class EmptyList<T : PeriodicTask> : PeriodicTaskResult<T>()
    data class Success<T : PeriodicTask>(val data: List<T>) : PeriodicTaskResult<T>()
    data class Error<T : PeriodicTask>(val data: Throwable) : PeriodicTaskResult<T>()
}