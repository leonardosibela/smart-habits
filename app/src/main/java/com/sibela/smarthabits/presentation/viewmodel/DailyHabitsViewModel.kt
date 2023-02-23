package com.sibela.smarthabits.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.usecase.FinishDailyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentDailyHabitsUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DailyHabitsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getCurrentDailyHabitsUseCase: GetCurrentDailyHabitsUseCase,
    private val finishDailyHabitUseCase: FinishDailyHabitUseCase
) : ViewModel() {

    companion object {
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val DAILY_HABITS_KEY = "daily_habits_key"
    }

    val habits: StateFlow<PeriodicHabitResult<DailyHabit>> = savedStateHandle.getStateFlow(
        DAILY_HABITS_KEY, PeriodicHabitResult.Loading
    )

    fun fetchHabits() = viewModelScope.launch {
        val result = getCurrentDailyHabitsUseCase()
        if (result is Result.Error) {
            setPeriodicHabitResult(PeriodicHabitResult.EmptyList)
        } else {
            if (result.result?.isEmpty() != false) {
                setPeriodicHabitResult(PeriodicHabitResult.EmptyList)
            } else {
                setPeriodicHabitResult(PeriodicHabitResult.Success(result.result))
            }
        }
    }

    private fun setPeriodicHabitResult(periodicHabitResult: PeriodicHabitResult<DailyHabit>) {
        savedStateHandle[DAILY_HABITS_KEY] = periodicHabitResult
    }

    fun finishHabit(habit: DailyHabit) = viewModelScope.launch {
        finishDailyHabitUseCase(habit)
        fetchHabits()
    }
}