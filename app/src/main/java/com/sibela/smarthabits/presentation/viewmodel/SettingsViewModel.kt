package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.usecase.*
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val resetDailyHabitsUseCase: ResetDailyHabitsUseCase,
    private val resetWeeklyHabitsUseCase: ResetWeeklyHabitsUseCase,
    private val resetMonthlyHabitsUseCase: ResetMonthlyHabitsUseCase,
    private val resetYearlyHabitsUseCase: ResetYearlyHabitsUseCase
) : ViewModel() {

    fun resetDailyHabits() = viewModelScope.launch {
        resetDailyHabitsUseCase()
    }

    fun resetWeeklyHabits() = viewModelScope.launch {
        resetWeeklyHabitsUseCase()
    }

    fun resetMonthlyHabits() = viewModelScope.launch {
        resetMonthlyHabitsUseCase()
    }

    fun resetYearlyHabits() = viewModelScope.launch {
        resetYearlyHabitsUseCase()
    }
}