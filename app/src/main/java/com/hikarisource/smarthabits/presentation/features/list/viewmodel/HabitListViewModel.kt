package com.hikarisource.smarthabits.presentation.features.list.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hikarisource.smarthabits.domain.common.Result
import com.hikarisource.smarthabits.domain.model.PeriodicHabit
import com.hikarisource.smarthabits.domain.usecase.FinishHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetCurrentHabitsUseCase
import com.hikarisource.smarthabits.presentation.features.common.viewmodel.DispatcherHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class HabitListViewModel<T : PeriodicHabit>(
    private val savedStateHandle: SavedStateHandle,
    private val getCurrentDailyHabitsUseCase: GetCurrentHabitsUseCase<T>,
    private val finishDailyHabitUseCase: FinishHabitUseCase<T>,
    private val dispatcherHandler: DispatcherHandler
) : ViewModel() {

    companion object {
        private const val DELAY_SETUP_DATABASE = 500L
    }

    @Suppress("PropertyName", "VariableNaming")
    abstract val HABITS_KEY: String

    @Suppress("LeakingThis")
    val habits: StateFlow<PeriodicHabitResult<T>> = savedStateHandle.getStateFlow(
        key = HABITS_KEY,
        initialValue = PeriodicHabitResult.Loading
    )

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var isFirstFetch = true

    fun fetchHabits(): Job = viewModelScope.launch(dispatcherHandler.IO) {
        setPeriodicHabitResult(PeriodicHabitResult.Loading)
        val result = getCurrentDailyHabitsUseCase()
        if (result is Result.Error) {
            if (isFirstFetch) {
                isFirstFetch = false
                delay(DELAY_SETUP_DATABASE)
                val secondResult = getCurrentDailyHabitsUseCase()
                if (secondResult is Result.Error) {
                    setPeriodicHabitResult(PeriodicHabitResult.Error)
                } else {
                    if (result.value?.isEmpty() != false) {
                        setPeriodicHabitResult(PeriodicHabitResult.EmptyList)
                    } else {
                        setPeriodicHabitResult(PeriodicHabitResult.Success(result.value))
                    }
                }
                fetchHabits()
            } else {
                setPeriodicHabitResult(PeriodicHabitResult.Error)
            }
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
