package com.sibela.smarthabits.presentation.viewmodel

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
        private const val MONTHLY_HABITS_KEY = "monthly_habits_key"
    }

    val habits: StateFlow<PeriodicHabitResult<MonthlyHabit>> = savedStateHandle.getStateFlow(
        MONTHLY_HABITS_KEY, PeriodicHabitResult.Loading
    )

    fun fetchHabits() = viewModelScope.launch {
        val result = getCurrentMonthlyHabitsUseCase()
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

    private fun setPeriodicHabitResult(periodicHabitResult: PeriodicHabitResult<MonthlyHabit>) {
        savedStateHandle[MONTHLY_HABITS_KEY] = periodicHabitResult
    }

    fun finishHabit(monthlyHabit: MonthlyHabit) = viewModelScope.launch {
        finishMonthlyHabitUseCase(monthlyHabit)
    }
}