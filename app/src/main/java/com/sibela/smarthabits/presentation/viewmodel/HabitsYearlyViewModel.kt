package com.sibela.smarthabits.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreYearlyUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HabitsYearlyViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getHabitsThatAreYearlyUseCase: GetHabitsThatAreYearlyUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase
) : ViewModel() {

    companion object {
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val HABITS_YEARLY_KEY = "habits_yearly_key"
    }

    val habits: StateFlow<HabitResult> = savedStateHandle.getStateFlow(
        HABITS_YEARLY_KEY, HabitResult.Loading
    )

    fun fetchHabits() = viewModelScope.launch {
        val result = getHabitsThatAreYearlyUseCase()
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
        savedStateHandle[HABITS_YEARLY_KEY] = habitResult
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