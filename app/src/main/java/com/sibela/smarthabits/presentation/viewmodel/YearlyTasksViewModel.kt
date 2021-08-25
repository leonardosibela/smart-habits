package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.YearlyTask
import com.sibela.smarthabits.domain.usecase.FinishYearlyTaskUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentYearlyTasksUseCase
import com.sibela.smarthabits.extension.asLiveData
import kotlinx.coroutines.launch

class YearlyTasksViewModel(
    private val getCurrentYearlyTasksUseCase: GetCurrentYearlyTasksUseCase,
    private val finishYearlyTaskUseCase: FinishYearlyTaskUseCase
) : ViewModel() {

    private val _tasks: MutableLiveData<PeriodicTaskResult<YearlyTask>> =
        MutableLiveData(PeriodicTaskResult.Loading())
    val tasks = _tasks.asLiveData

    init {
        viewModelScope.launch {
            val result = getCurrentYearlyTasksUseCase()
            if (result is Result.Error) {
                _tasks.value = PeriodicTaskResult.Error(result.throwable)
            } else {
                _tasks.value = PeriodicTaskResult.Success(result.result ?: emptyList())
            }
        }
    }

    fun finish(yearlyTask: YearlyTask) = viewModelScope.launch {
        finishYearlyTaskUseCase(yearlyTask)
    }
}