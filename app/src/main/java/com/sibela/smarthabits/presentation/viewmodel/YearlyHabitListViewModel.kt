package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreYearlyUseCase
import com.sibela.smarthabits.extension.asLiveData
import kotlinx.coroutines.launch

class YearlyHabitListViewModel(
    private val getHabitsThatAreYearlyUseCase: GetHabitsThatAreYearlyUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase
) : ViewModel() {

    private val _habits: MutableLiveData<HabitResult> =
        MutableLiveData(HabitResult.Loading)
    val habits = _habits.asLiveData

    init {
        viewModelScope.launch {
            val result = getHabitsThatAreYearlyUseCase()
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

    fun deleteHbait(habit: Habit) = viewModelScope.launch {
        deleteHabitUseCase(habit)
    }
}