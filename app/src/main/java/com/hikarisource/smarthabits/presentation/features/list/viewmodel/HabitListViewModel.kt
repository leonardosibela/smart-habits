package com.hikarisource.smarthabits.presentation.features.list.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hikarisource.smarthabits.domain.common.Result
import com.hikarisource.smarthabits.domain.model.PeriodicHabit
import com.hikarisource.smarthabits.domain.usecase.FinishHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetCurrentHabitsUseCase
import com.hikarisource.smarthabits.presentation.features.common.viewmodel.DispatcherHandler
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class HabitListViewModel<T : PeriodicHabit>(
    private val savedStateHandle: SavedStateHandle,
    private val getCurrentDailyHabitsUseCase: GetCurrentHabitsUseCase<T>,
    private val finishDailyHabitUseCase: FinishHabitUseCase<T>,
    private val dispatcherHandler: DispatcherHandler,
) : ViewModel() {

    @Suppress("PropertyName")
    abstract val HABITS_KEY: String

    @Suppress("LeakingThis")
    val habits: StateFlow<PeriodicHabitResult<T>> = savedStateHandle.getStateFlow(
        HABITS_KEY, PeriodicHabitResult.Loading
    )

    fun fetchHabits() = viewModelScope.launch(dispatcherHandler.IO) {
        setPeriodicHabitResult(PeriodicHabitResult.Loading)
        val result = getCurrentDailyHabitsUseCase()
        if (result is Result.Error) {
            setPeriodicHabitResult(PeriodicHabitResult.Error)
        } else {
            if (result.value?.isEmpty() != false) {
                setPeriodicHabitResult(PeriodicHabitResult.EmptyList)
            } else {
                setPeriodicHabitResult(PeriodicHabitResult.Success(result.value))
            }
        }
    }

    private fun setPeriodicHabitResult(periodicHabitResult: PeriodicHabitResult<T>) {
        savedStateHandle[HABITS_KEY] = periodicHabitResult
    }

    fun finishHabit(habit: T) = viewModelScope.launch(dispatcherHandler.IO) {
        finishDailyHabitUseCase(habit)
        fetchHabits()
    }
}