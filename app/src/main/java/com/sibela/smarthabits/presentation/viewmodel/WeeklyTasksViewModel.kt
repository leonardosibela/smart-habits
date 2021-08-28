package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.WeeklyTask
import com.sibela.smarthabits.domain.usecase.FinishWeeklyTaskUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentWeeklyTasksUseCase
import com.sibela.smarthabits.extension.asLiveData
import kotlinx.coroutines.launch

class WeeklyTasksViewModel(
    private val getCurrentWeeklyTasksUseCase: GetCurrentWeeklyTasksUseCase,
    private val finishWeeklyTaskUseCase: FinishWeeklyTaskUseCase
) : ViewModel() {

    private val _tasks: MutableLiveData<PeriodicTaskResult<WeeklyTask>> =
        MutableLiveData(PeriodicTaskResult.Loading())
    val tasks = _tasks.asLiveData

    init {
        fetchTasks()
    }

    fun fetchTasks() = viewModelScope.launch {
        val result = getCurrentWeeklyTasksUseCase()
        if (result is Result.Error) {
            _tasks.value = PeriodicTaskResult.Error(result.throwable)
        } else {
            if (result.result?.isEmpty() ?: true) {
                _tasks.value = PeriodicTaskResult.EmptyList()
            } else {
                _tasks.value = PeriodicTaskResult.Success(result.result ?: emptyList())
            }
        }
    }

    fun finish(weeklyTask: WeeklyTask) = viewModelScope.launch {
        finishWeeklyTaskUseCase(weeklyTask)
    }
}