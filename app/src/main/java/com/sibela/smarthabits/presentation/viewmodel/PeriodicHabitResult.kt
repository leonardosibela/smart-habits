package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.model.PeriodicHabit

sealed class PeriodicHabitResult<out T : PeriodicHabit> {
    object Loading : PeriodicHabitResult<Nothing>()
    object EmptyList : PeriodicHabitResult<Nothing>()
    data class Success<T : PeriodicHabit>(val data: List<T>) : PeriodicHabitResult<T>()
}