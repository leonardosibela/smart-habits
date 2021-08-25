package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.Task
import com.sibela.smarthabits.domain.usecase.DeleteTaskUseCase
import com.sibela.smarthabits.domain.usecase.GetTasksThatAreMonthlyUseCase
import com.sibela.smarthabits.extension.asLiveData
import kotlinx.coroutines.launch

class MonthlyTaskListViewModel(
    private val getTasksThatAreMonthlyUseCase: GetTasksThatAreMonthlyUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val _tasks: MutableLiveData<TaskResult> =
        MutableLiveData(TaskResult.Loading)
    val tasks = _tasks.asLiveData

    init {
        viewModelScope.launch {
            val result = getTasksThatAreMonthlyUseCase()
            if (result is Result.Error) {
                _tasks.value = TaskResult.Error(result.throwable)
            } else {
                if (result.result?.isEmpty() ?: true) {
                    _tasks.value = TaskResult.EmptyList
                } else {
                    _tasks.value = TaskResult.Success(result.result ?: emptyList())
                }
            }
        }
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        deleteTaskUseCase(task)
    }
}