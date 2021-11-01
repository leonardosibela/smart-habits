package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.usecase.FinishMonthlyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentMonthlyHabitsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MonthlyHabitsViewModel(
    private val getCurrentMonthlyHabitsUseCase: GetCurrentMonthlyHabitsUseCase,
    private val finishMonthlyHabitUseCase: FinishMonthlyHabitUseCase
) : ViewModel() {

    private val _habits: MutableStateFlow<PeriodicHabitResult<MonthlyHabit>> =
        MutableStateFlow(PeriodicHabitResult.Loading)
    val habits = _habits.asStateFlow()

    fun fetchHabits() = viewModelScope.launch {
        val result = getCurrentMonthlyHabitsUseCase()
        if (result is Result.Error) {
            _habits.value = PeriodicHabitResult.EmptyList
        } else {
            if (result.result?.isEmpty() != false) {
                _habits.value = PeriodicHabitResult.EmptyList
            } else {
                _habits.value = PeriodicHabitResult.Success(result.result ?: emptyList())
            }
        }
    }

    fun finishHabit(monthlyHabit: MonthlyHabit) = viewModelScope.launch {
        finishMonthlyHabitUseCase(monthlyHabit)
    }
}