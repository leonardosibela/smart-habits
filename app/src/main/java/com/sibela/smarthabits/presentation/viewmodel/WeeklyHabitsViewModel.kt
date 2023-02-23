package com.sibela.smarthabits.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.usecase.FinishWeeklyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentWeeklyHabitsUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeeklyHabitsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getCurrentWeeklyHabitsUseCase: GetCurrentWeeklyHabitsUseCase,
    private val finishWeeklyHabitUseCase: FinishWeeklyHabitUseCase
) : ViewModel() {

    companion object {
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val WEEKLY_HABIT_KEY = "weekly_habit_key"
    }

    val habits: StateFlow<PeriodicHabitResult<WeeklyHabit>> = savedStateHandle.getStateFlow(
        WEEKLY_HABIT_KEY, PeriodicHabitResult.Loading
    )

    fun fetchHabits() = viewModelScope.launch {
        when(val result = getCurrentWeeklyHabitsUseCase()) {
            is Result.Error -> setPeriodicHabitResult(PeriodicHabitResult.EmptyList)
            is Result.Success -> {
                if (result.data.isEmpty()) {
                    setPeriodicHabitResult(PeriodicHabitResult.EmptyList)
                } else {
                    setPeriodicHabitResult(PeriodicHabitResult.Success(result.data))
                }
            }
        }
    }

    private fun setPeriodicHabitResult(periodicHabitResult: PeriodicHabitResult<WeeklyHabit>) {
        savedStateHandle[WEEKLY_HABIT_KEY] = periodicHabitResult
    }

    fun finishHabit(weeklyHabit: WeeklyHabit) = viewModelScope.launch {
        finishWeeklyHabitUseCase(weeklyHabit)
        fetchHabits()
    }
}