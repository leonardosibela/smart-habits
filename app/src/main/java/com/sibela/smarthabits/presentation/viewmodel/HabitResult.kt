package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.model.Habit

sealed class HabitResult {
    object Loading : HabitResult()
    object EmptyList : HabitResult()
    data class Success(val data: List<Habit>) : HabitResult()
    data class Error(val data: Throwable) : HabitResult()
}