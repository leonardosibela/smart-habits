package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.DailyTask
import com.sibela.smarthabits.domain.usecase.GetCurrentDailyTasksUseCase
import com.sibela.smarthabits.extension.asLiveData
import kotlinx.coroutines.launch

class DailyTasksViewModel(
    private val getCurrentDailyTasksUseCase: GetCurrentDailyTasksUseCase
) : ViewModel() {

    private val _tasks: MutableLiveData<PeriodicTaskResult<DailyTask>> =
        MutableLiveData(PeriodicTaskResult.Loading())
    val tasks = _tasks.asLiveData

    init {
        viewModelScope.launch {
            val result = getCurrentDailyTasksUseCase()
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
    }
}