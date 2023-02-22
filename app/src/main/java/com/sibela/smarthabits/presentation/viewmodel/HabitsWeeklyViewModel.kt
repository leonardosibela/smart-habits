package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreWeeklyUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HabitsWeeklyViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getHabitsThatAreWeeklyUseCase: GetHabitsThatAreWeeklyUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase
) : ViewModel() {

    companion object {
        private const val HABITS_WEEKLY_KEY = "habits_weekly_key"
    }

    val habits: StateFlow<HabitResult> = savedStateHandle.getStateFlow(
        HABITS_WEEKLY_KEY, HabitResult.EmptyList
    )

    fun fetchHabits() = viewModelScope.launch {
        val result = getHabitsThatAreWeeklyUseCase()
        if (result is Result.Error) {
            setHabitResult(HabitResult.Error(result.throwable))
        } else {
            if (result.result?.isEmpty() != false) {
                setHabitResult(HabitResult.EmptyList)
            } else {
                setHabitResult(HabitResult.Success(result.result))
            }
        }
    }

    private fun setHabitResult(habitResult: HabitResult) {
        savedStateHandle[HABITS_WEEKLY_KEY] = habitResult
    }

    fun deleteHabit(habit: Habit) = viewModelScope.launch {
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