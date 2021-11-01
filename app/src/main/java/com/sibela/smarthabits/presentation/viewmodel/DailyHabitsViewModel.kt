package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.usecase.FinishDailyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentDailyHabitsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DailyHabitsViewModel(
    private val getCurrentDailyHabitsUseCase: GetCurrentDailyHabitsUseCase,
    private val finishDailyHabitUseCase: FinishDailyHabitUseCase
) : ViewModel() {

    private val _habits: MutableStateFlow<PeriodicHabitResult<DailyHabit>> =
        MutableStateFlow(PeriodicHabitResult.Loading)
    val habits = _habits.asStateFlow()

    fun fetchHabits() = viewModelScope.launch {
        val result = getCurrentDailyHabitsUseCase()
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

    fun finishHabit(habit: DailyHabit) = viewModelScope.launch {
        finishDailyHabitUseCase(habit)
    }
}