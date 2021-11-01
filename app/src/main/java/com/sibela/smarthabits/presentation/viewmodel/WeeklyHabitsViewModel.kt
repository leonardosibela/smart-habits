package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.usecase.FinishWeeklyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentWeeklyHabitsUseCase
import com.sibela.smarthabits.extension.asLiveData
import kotlinx.coroutines.launch

class WeeklyHabitsViewModel(
    private val getCurrentWeeklyHabitsUseCase: GetCurrentWeeklyHabitsUseCase,
    private val finishWeeklyHabitUseCase: FinishWeeklyHabitUseCase
) : ViewModel() {

    private val _habits: MutableLiveData<PeriodicHabitResult<WeeklyHabit>> =
        MutableLiveData(PeriodicHabitResult.Loading)
    val habits = _habits.asLiveData

    fun fetchHabits() = viewModelScope.launch {
        val result = getCurrentWeeklyHabitsUseCase()
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

    fun finishHabit(weeklyHabit: WeeklyHabit) = viewModelScope.launch {
        finishWeeklyHabitUseCase(weeklyHabit)
    }
}