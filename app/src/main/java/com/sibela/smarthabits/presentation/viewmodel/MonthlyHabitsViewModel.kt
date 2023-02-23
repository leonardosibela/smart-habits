package com.sibela.smarthabits.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.usecase.FinishMonthlyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentMonthlyHabitsUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MonthlyHabitsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getCurrentMonthlyHabitsUseCase: GetCurrentMonthlyHabitsUseCase,
    private val finishMonthlyHabitUseCase: FinishMonthlyHabitUseCase
) : ViewModel() {

    companion object {
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val MONTHLY_HABITS_KEY = "monthly_habits_key"
    }

    val habits: StateFlow<PeriodicHabitResult<MonthlyHabit>> = savedStateHandle.getStateFlow(
        MONTHLY_HABITS_KEY, PeriodicHabitResult.Loading
    )

    fun fetchHabits() = viewModelScope.launch {
        when(val result = getCurrentMonthlyHabitsUseCase()) {
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

    private fun setPeriodicHabitResult(periodicHabitResult: PeriodicHabitResult<MonthlyHabit>) {
        savedStateHandle[MONTHLY_HABITS_KEY] = periodicHabitResult
    }

    fun finishHabit(monthlyHabit: MonthlyHabit) = viewModelScope.launch {
        finishMonthlyHabitUseCase(monthlyHabit)
        fetchHabits()
    }
}