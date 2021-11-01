package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.usecase.FinishYearlyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentYearlyHabitsUseCase
import com.sibela.smarthabits.extension.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class YearlyHabitsViewModel(
    private val getCurrentYearlyHabitsUseCase: GetCurrentYearlyHabitsUseCase,
    private val finishYearlyHabitUseCase: FinishYearlyHabitUseCase
) : ViewModel() {

    private val _habits: MutableStateFlow<PeriodicHabitResult<YearlyHabit>> =
        MutableStateFlow(PeriodicHabitResult.Loading)
    val habits = _habits.asStateFlow()

    fun fetchHabits() = viewModelScope.launch {
        val result = getCurrentYearlyHabitsUseCase()
        if (result is Result.Error) {
            _habits.value = PeriodicHabitResult.EmptyList
        } else {
            if (result.result?.isEmpty() != false) {
                _habits.value = PeriodicHabitResult.EmptyList
            } else {
                _habits.value = PeriodicHabitResult.Success(result.result)
            }
        }
    }

    fun finishHabit(yearlyHabit: YearlyHabit) = viewModelScope.launch {
        finishYearlyHabitUseCase(yearlyHabit)
    }
}