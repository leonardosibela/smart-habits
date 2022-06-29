package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.usecase.ResetDailyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetMonthlyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetWeeklyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetYearlyHabitsUseCase
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val resetDailyHabitsUseCase: ResetDailyHabitsUseCase,
    private val resetWeeklyHabitsUseCase: ResetWeeklyHabitsUseCase,
    private val resetMonthlyHabitsUseCase: ResetMonthlyHabitsUseCase,
    private val resetYearlyHabitsUseCase: ResetYearlyHabitsUseCase,
) : ViewModel() {

    fun resetDailyHabits() = viewModelScope.launch {
        resetDailyHabitsUseCase.invoke()
    }

    fun resetWeeklyHabits() = viewModelScope.launch {
        resetWeeklyHabitsUseCase.invoke()
    }

    fun resetMonthlyHabits() = viewModelScope.launch {
        resetMonthlyHabitsUseCase.invoke()
    }

    fun resetYearlyHabits() = viewModelScope.launch {
        resetYearlyHabitsUseCase.invoke()
    }
}