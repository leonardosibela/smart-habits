package com.sibela.smarthabits.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.usecase.FinishYearlyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentYearlyHabitsUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class YearlyHabitsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getCurrentYearlyHabitsUseCase: GetCurrentYearlyHabitsUseCase,
    private val finishYearlyHabitUseCase: FinishYearlyHabitUseCase
) : ViewModel() {

    companion object {
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val YEARLY_HABIT_KEY = "yearly_habit_key"
    }

    val habits: StateFlow<PeriodicHabitResult<YearlyHabit>> = savedStateHandle.getStateFlow(
        YEARLY_HABIT_KEY, PeriodicHabitResult.Loading
    )

    fun fetchHabits() = viewModelScope.launch {
        when(val result = getCurrentYearlyHabitsUseCase()) {
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

    private fun setPeriodicHabitResult(periodicHabitResult: PeriodicHabitResult<YearlyHabit>) {
        savedStateHandle[YEARLY_HABIT_KEY] = periodicHabitResult
    }

    fun finishHabit(yearlyHabit: YearlyHabit) = viewModelScope.launch {
        finishYearlyHabitUseCase(yearlyHabit)
        fetchHabits()
    }
}