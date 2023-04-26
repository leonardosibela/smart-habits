package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hikarisource.smarthabits.domain.common.Result
import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.domain.usecase.DeleteHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsFromPeriodUseCase
import com.hikarisource.smarthabits.presentation.features.common.viewmodel.DispatcherHandler
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class HabitsSettingsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getHabitsFromPeriod: GetHabitsFromPeriodUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val dispatcherHandler: DispatcherHandler
) : ViewModel() {

    companion object {
        const val HABITS_KEY = "habits_key"
    }

    @Suppress("LeakingThis")
    val habits: StateFlow<HabitResult> = savedStateHandle.getStateFlow(
        HABITS_KEY, HabitResult.Loading
    )

    fun fetchHabits() = viewModelScope.launch(dispatcherHandler.IO) {
        val result = getHabitsFromPeriod()
        if (result is Result.Error) {
            setHabitResult(HabitResult.Error(result.throwable))
        } else {
            if (result.value?.isEmpty() != false) {
                setHabitResult(HabitResult.EmptyList)
            } else {
                setHabitResult(HabitResult.Success(result.value))
            }
        }
    }

    private fun setHabitResult(habitResult: HabitResult) {
        savedStateHandle[HABITS_KEY] = habitResult
    }

    fun deleteHabit(habit: Habit) = viewModelScope.launch(dispatcherHandler.IO) {
        deleteHabitUseCase(habit)
        runCatching {
            (habits.value as HabitResult.Success).data
        }.getOrNull()?.also {
            ArrayList(it).apply { remove(habit) }.also { list ->
                setHabitResult(HabitResult.Success(list))
            }
        }
    }
}
