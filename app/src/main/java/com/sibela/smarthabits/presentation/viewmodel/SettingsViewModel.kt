package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.usecase.*
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val resetDailyTasksUseCase: ResetDailyTasksUseCase,
    private val resetWeeklyTasksUseCase: ResetWeeklyTasksUseCase,
    private val resetMonthlyTasksUseCase: ResetMonthlyTasksUseCase,
    private val resetYearlyTasksUseCase: ResetYearlyTasksUseCase
) : ViewModel() {

    fun resetDailyTasks() = viewModelScope.launch {
        resetDailyTasksUseCase()
    }

    fun resetWeeklyTasks() = viewModelScope.launch {
        resetWeeklyTasksUseCase()
    }

    fun resetMonthlyTasks() = viewModelScope.launch {
        resetMonthlyTasksUseCase()
    }

    fun resetYearlyTasks() = viewModelScope.launch {
        resetYearlyTasksUseCase()
    }
}