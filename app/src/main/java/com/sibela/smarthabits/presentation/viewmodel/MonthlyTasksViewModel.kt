package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.MonthlyTask
import com.sibela.smarthabits.domain.usecase.FinishMonthlyTaskUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentMonthlyTasksUseCase
import com.sibela.smarthabits.extension.asLiveData
import kotlinx.coroutines.launch

class MonthlyTasksViewModel(
    private val getCurrentMonthlyTasksUseCase: GetCurrentMonthlyTasksUseCase,
    private val finishMonthlyTaskUseCase: FinishMonthlyTaskUseCase
) : ViewModel() {

    private val _tasks: MutableLiveData<PeriodicTaskResult<MonthlyTask>> =
        MutableLiveData(PeriodicTaskResult.Loading())
    val tasks = _tasks.asLiveData

    init {
        fetchTasks()
    }

    fun fetchTasks() = viewModelScope.launch {
        val result = getCurrentMonthlyTasksUseCase()
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

    fun finishTask(monthlyTask: MonthlyTask) = viewModelScope.launch {
        finishMonthlyTaskUseCase(monthlyTask)
    }
}