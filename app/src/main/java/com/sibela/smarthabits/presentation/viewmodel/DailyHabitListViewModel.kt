package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreDailyUseCase
import com.sibela.smarthabits.extension.asLiveData
import kotlinx.coroutines.launch

class DailyHabitListViewModel(
    private val getHabitsThatAreDailyUseCase: GetHabitsThatAreDailyUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase
) : ViewModel() {

    private val _habits: MutableLiveData<HabitResult> =
        MutableLiveData(HabitResult.Loading)
    val habits = _habits.asLiveData

    init {
        viewModelScope.launch {
            val result = getHabitsThatAreDailyUseCase()
            if (result is Result.Error) {
                _habits.value = HabitResult.Error(result.throwable)
            } else {
                if (result.result?.isEmpty() ?: true) {
                    _habits.value = HabitResult.EmptyList
                } else {
                    _habits.value = HabitResult.Success(result.result ?: emptyList())
                }
            }
        }
    }

    fun deleteHabit(habit: Habit) = viewModelScope.launch {
        deleteHabitUseCase(habit)
    }
}