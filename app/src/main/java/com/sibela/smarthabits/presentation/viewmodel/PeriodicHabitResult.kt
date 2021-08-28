package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.model.PeriodicHabit

sealed class PeriodicHabitResult<T : PeriodicHabit> {
    class Loading<T : PeriodicHabit> : PeriodicHabitResult<T>()
    class EmptyList<T : PeriodicHabit> : PeriodicHabitResult<T>()
    data class Success<T : PeriodicHabit>(val data: List<T>) : PeriodicHabitResult<T>()
    data class Error<T : PeriodicHabit>(val data: Throwable) : PeriodicHabitResult<T>()
}